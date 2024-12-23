package br.dev.s2w.jfoods.api.adapter.model;

import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@JsonRootName(("cuisines"))
//@JacksonXmlRootElement(localName = "cuisines")
@Data
public class CuisinesXmlWrapper {
    @JsonProperty("cuisine")
    //@JacksonXmlProperty(localName = "cuisine")
    @JacksonXmlElementWrapper(useWrapping = false)
    @NonNull
    private List<Cuisine> cuisines;
}
