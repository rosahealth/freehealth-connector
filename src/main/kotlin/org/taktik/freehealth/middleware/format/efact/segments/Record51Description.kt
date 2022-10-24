/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.freehealth.middleware.format.efact.segments

import java.util.LinkedHashMap

object Record51Description : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(65)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "1", "EnregistrementType", "recordType", "N", pos, 2, "51")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2", "numero d'ordre de l'enregistrement", "recordOrderNumber", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3", "reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4", "code nomenclature ou pseudo-code nomenclature", "prestationCode", "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5", "date prestation", "prestationDate", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "6a", "reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "6b", "reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "7", "reserve", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "8a,8b", "identification beneficiaire", "recipientIdentifier", "N", pos, 13)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "9", "reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "10", "reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "11", "reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "12", "reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "13", "reserve", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "14", "reserve", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "15", "identification du dispensateur", "careProviderIdentifier", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "16", "reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "17", "reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "18", "reserve", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "19", "signe + montant intervention de l'assurance", "reimbursementAmount", "A", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "20", "reserve", null, "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "21", "reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "22", "reserve", null, "N", pos, 5)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "23", "reserve", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "24", "reserve", null, "N", pos, 5)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "25", "reserve", null, "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "26", "reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "27", "ct1 + ct2", "ct1ct2", "N", pos, 10)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "28", "reserve", null, "N", pos, 25)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "29", "reserve", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "30", "reserve", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "31", "reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "32", "reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "33", "reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "34", "reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "35", "reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "36", "reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "37", "reserve", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "38", "reserve", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "39", "reserve", null, "N", pos, 10)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "40", "reserve", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "41", "reserve", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "42,43a,43b,44,45", "donnees de reference reseau", "networkReferenceData", "A", pos, 48)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "46", "reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "47", "reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "48", "reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "49", "reserve", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "50", "reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "51", "reserve", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "52", "reserve", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "53", "reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "54a", "reserve", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "54b", "reserve", null, "N", pos, 5)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "55", "date communication information", "infoCommunicationDate", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "56", "reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "57", "reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "58", "reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "59", "reserve", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "98", "reserve", null, "N", pos, 2)
              register(ZONE_DESCRIPTIONS_BY_ZONE, "99", "Chiffres de controle de l'enregistrement", null, "N", pos, 2, null, true)
    }
}
