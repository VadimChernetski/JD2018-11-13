//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.01.16 at 01:31:21 AM MSK 
//


package by.it.mnovikov.jd02_09.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Ad complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ad">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="discription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sampler_type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sampler_title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productivity" type="{http://beans.jd02_09.mnovikov.it.by}Productivity"/>
 *         &lt;element name="price" type="{http://beans.jd02_09.mnovikov.it.by}Price"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ad", propOrder = {
    "title",
    "discription",
    "samplerType",
    "samplerTitle",
    "productivity",
    "price"
})
public class Ad {

    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String discription;
    @XmlElement(name = "sampler_type", required = true)
    protected String samplerType;
    @XmlElement(name = "sampler_title", required = true)
    protected String samplerTitle;
    @XmlElement(required = true)
    protected Productivity productivity;
    @XmlElement(required = true)
    protected Price price;

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the discription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscription() {
        return discription;
    }

    /**
     * Sets the value of the discription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscription(String value) {
        this.discription = value;
    }

    /**
     * Gets the value of the samplerType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSamplerType() {
        return samplerType;
    }

    /**
     * Sets the value of the samplerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSamplerType(String value) {
        this.samplerType = value;
    }

    /**
     * Gets the value of the samplerTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSamplerTitle() {
        return samplerTitle;
    }

    /**
     * Sets the value of the samplerTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSamplerTitle(String value) {
        this.samplerTitle = value;
    }

    /**
     * Gets the value of the productivity property.
     * 
     * @return
     *     possible object is
     *     {@link Productivity }
     *     
     */
    public Productivity getProductivity() {
        return productivity;
    }

    /**
     * Sets the value of the productivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Productivity }
     *     
     */
    public void setProductivity(Productivity value) {
        this.productivity = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link Price }
     *     
     */
    public Price getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link Price }
     *     
     */
    public void setPrice(Price value) {
        this.price = value;
    }

}
