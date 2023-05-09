/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.freehealth.middleware.dto.eattest

import org.joda.time.DateTime
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation

class SendAttestResultWithResponse(
    acknowledge: EattestAcknowledgeType? = null,
    invoicingNumber: String? = null,
    attest: Eattest? = null,
    var xades: ByteArray?,
    commonOutput: CommonOutput? = null,
    mycarenetConversation: MycarenetConversation? = null,
    var kmehrMessage: ByteArray?,
    timestamp: String? = null
): SendAttestResult(acknowledge, invoicingNumber, attest, commonOutput, mycarenetConversation, timestamp)
