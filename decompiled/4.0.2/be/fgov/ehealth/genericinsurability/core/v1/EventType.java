package be.fgov.ehealth.genericinsurability.core.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "EventType"
)
@XmlEnum
public enum EventType {
   @XmlEnumValue("changeDuring")
   CHANGE_DURING("changeDuring"),
   @XmlEnumValue("multiple")
   MULTIPLE("multiple"),
   @XmlEnumValue("closedBefore")
   CLOSED_BEFORE("closedBefore"),
   @XmlEnumValue("startLater")
   START_LATER("startLater");

   private final String value;

   private EventType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static EventType fromValue(String v) {
      EventType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         EventType c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
