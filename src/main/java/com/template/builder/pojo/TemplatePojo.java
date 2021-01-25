package com.template.builder.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"_id :"+
"_rev :"+
"metaData :"+
"name :"+
"html"
})
public class TemplatePojo {

@JsonProperty("_id")
private String _id;
@JsonProperty("_rev")
private String _rev;
@JsonProperty("metaData")
private Map<String, Object> metaData = null;
@JsonProperty("name")
private String name;
@JsonProperty("html")
private String html;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("_id")
public String get_id() {
    return _id;
}
public void set_id(String _id) {
    this._id = _id;
}

public String get_rev() {
    return _rev;
}
public void set_rev(String _rev) {
    this._rev = _rev;
}

@JsonProperty("metaData")
public Map<String, Object> getMetaData() {
return metaData;
}

@JsonProperty("metaData")
public void setMetaData(Map<String, Object> metaData) {
this.metaData = metaData;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("html")
public String getHtml() {
return html;
}

@JsonProperty("html")
public void setHtml(String html) {
this.html = html;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

@Override
public String toString() {
return new StringBuilder().append("id :"+ _id).append("rev :"+ _rev).append("metaData :"+ metaData).append("name :"+ name).append("html :"+ html).append("additionalProperties :"+ additionalProperties).toString();
}




}


class MetaDatum {

@JsonProperty("type")
private String type;
@JsonProperty("subtype")
private String subtype;
@JsonProperty("label")
private String label;
@JsonProperty("className")
private String className;
@JsonProperty("name")
private String name;
@JsonProperty("userData")
private List<String> userData = null;
@JsonProperty("values")
private List<Value> values = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("subtype")
public String getSubtype() {
return subtype;
}

@JsonProperty("subtype")
public void setSubtype(String subtype) {
this.subtype = subtype;
}

@JsonProperty("label")
public String getLabel() {
return label;
}

@JsonProperty("label")
public void setLabel(String label) {
this.label = label;
}

@JsonProperty("className")
public String getClassName() {
return className;
}

@JsonProperty("className")
public void setClassName(String className) {
this.className = className;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("userData")
public List<String> getUserData() {
return userData;
}

@JsonProperty("userData")
public void setUserData(List<String> userData) {
this.userData = userData;
}

@JsonProperty("values")
public List<Value> getValues() {
return values;
}

@JsonProperty("values")
public void setValues(List<Value> values) {
this.values = values;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

@Override
public String toString() {
return new StringBuilder().append("type :"+ type).append("subtype :"+ subtype).append("label :"+ label).append("className :"+ className).append("name :"+ name).append("userData :"+ userData).append("values :"+ values).append("additionalProperties :"+ additionalProperties).toString();
}


}

class Value {

@JsonProperty("label")
private String label;
@JsonProperty("value")
private String val;
@JsonProperty("selected")
private Boolean selected;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("label")
public String getLabel() {
return label;
}

@JsonProperty("label")
public void setLabel(String label) {
this.label = label;
}

@JsonProperty("value")
public String getValue() {
return val;
}

@JsonProperty("value")
public void setValue(String value) {
this.val = value;
}

@JsonProperty("selected")
public Boolean getSelected() {
return selected;
}

@JsonProperty("selected")
public void setSelected(Boolean selected) {
this.selected = selected;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

@Override
public String toString() {
return new StringBuilder().append("label :"+ label).append("value :"+ val).append("selected :"+ selected).append("additionalProperties :"+ additionalProperties).toString();
}



}