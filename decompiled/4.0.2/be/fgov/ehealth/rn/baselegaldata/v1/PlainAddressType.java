package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PlainAddressType"
)
public class PlainAddressType extends PlainAddressOptionalInceptionDateType implements Serializable {
   private static final long serialVersionUID = 1L;

   public PlainAddressType() {
   }
}
