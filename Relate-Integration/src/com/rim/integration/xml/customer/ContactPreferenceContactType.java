//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.31 at 12:23:47 PM IST 
//


package com.rim.integration.xml.customer;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 * <p>Java class for ContactPreferenceContactType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ContactPreferenceContactType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Mail"/>
 *     &lt;enumeration value="Email"/>
 *     &lt;enumeration value="Phone"/>
 *     &lt;enumeration value="Fax"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum ContactPreferenceContactType {

    @XmlEnumValue("Email")
    EMAIL("Email"),
    @XmlEnumValue("Fax")
    FAX("Fax"),
    @XmlEnumValue("Mail")
    MAIL("Mail"),
    @XmlEnumValue("Phone")
    PHONE("Phone");
    private final String value;

    ContactPreferenceContactType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ContactPreferenceContactType fromValue(String v) {
        for (ContactPreferenceContactType c: ContactPreferenceContactType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
