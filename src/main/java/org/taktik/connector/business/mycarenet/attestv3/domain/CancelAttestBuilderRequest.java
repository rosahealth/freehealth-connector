package org.taktik.connector.business.mycarenet.attestv3.domain;

import be.fgov.ehealth.messageservices.core.v1.SendTransactionRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.CancelAttestationRequest;

public class CancelAttestBuilderRequest {
   private SendTransactionRequest sendTransactionRequest;
   private CancelAttestationRequest cancelAttestationRequest;

   public CancelAttestBuilderRequest(CancelAttestationRequest cancelAttestationRequest, SendTransactionRequest sendTransactionRequest) {
      this.cancelAttestationRequest = cancelAttestationRequest;
      this.sendTransactionRequest = sendTransactionRequest;
   }

   public SendTransactionRequest getSendTransactionRequest() {
      return this.sendTransactionRequest;
   }

   public CancelAttestationRequest getCancelAttestationRequest() {
      return this.cancelAttestationRequest;
   }
}
