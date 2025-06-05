package com.example.restapi.service;

import com.example.restapi.dto.FoodInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.springframework.web.client.RestTemplate;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final String API_KEY = "인증키값";

    public List<FoodInfoDto> getFoodList(String sigun) {
        RestTemplate restTemplate = new RestTemplate();
        String response = getApiResponse();
        List<FoodInfoDto> all = parseXml(response);

        return Optional.ofNullable(sigun)
                .filter(s -> !s.isEmpty())
                .map(s -> filterBySigun(all, s))
                .orElse(all);
    }

    private String getApiResponse() {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://openapi.gg.go.kr/Genrestrtcate")
                .queryParam("KEY", API_KEY)
                .queryParam("Type", "xml")
                .queryParam("pIndex", 1)
                .queryParam("pSize", 100)
                .toUriString();

        return new RestTemplate().getForObject(url, String.class);
    }

    private List<FoodInfoDto> filterBySigun(List<FoodInfoDto> list, String sigun) {
        return list.stream()
                .filter(dto -> dto.getRefineRoadnmAddr() != null &&
                        dto.getRefineRoadnmAddr().contains(sigun))
                .collect(Collectors.toList());
    }

    private List<FoodInfoDto> parseXml(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));

            return xmlToDtoList(doc);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private List<FoodInfoDto> xmlToDtoList(Document doc) {
        NodeList rows = doc.getElementsByTagName("row");
        return IntStream.range(0, rows.getLength())
                .mapToObj(rows::item)
                .map(node -> (Element) node)
                .map(this::createFoodDto)
                .collect(Collectors.toList());
    }

    private FoodInfoDto createFoodDto(Element element) {
        return new FoodInfoDto(
                getTagValue("BIZPLC_NM", element),
                getTagValue("REFINE_ROADNM_ADDR", element),
                getTagValue("REFINE_LOTNO_ADDR", element),
                getTagValue("REFINE_WGS84_LAT", element),
                getTagValue("REFINE_WGS84_LOGT", element)
        );
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        return nodeList.getLength() > 0 ?
                nodeList.item(0).getTextContent() :
                null;
    }
}