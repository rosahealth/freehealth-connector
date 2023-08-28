package org.taktik.connector.business.mycarenet.attestv3.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.TokenType;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.taktik.connector.technical.ws.impl.AbstractWsSender;

public final class ServiceFactory {
   private static final String PROP_ENDPOINT_ATTEST_V3 = "endpoint.eattestv3";
   public static final String PROP_MESSAGE_PROTOCOLE_SCHEMA_VERSION_V3 = "mycarenet.attest.v3.message.protocole.schema.version";
   private static final List<String> expectedProps = new ArrayList();
   private static final Configuration config;
   private static final Log log = LogFactory.getLog(AbstractWsSender.class);

   private ServiceFactory() {
   }

   public static GenericRequest getAttestPort(SAMLToken token, String soapAction) throws TechnicalConnectorException {
      Validate.notNull(token, "Required parameter SAMLToken is null.");
      String endPoint = config.getProperty("endpoint.eattestv3", "$uddi{uddi:ehealth-fgov-be:business:mycareneteattest:v3}");

      log.info("--------------------new endpoint--------------------------");
      log.info(endPoint);

      return (new GenericRequest()).setEndpoint(endPoint).setCredential(token, TokenType.SAML).setSoapAction(soapAction).addDefaulHandlerChain();
   }

   static {
      config = ConfigFactory.getConfigValidator(expectedProps);
   }
}