package org.taktik.connector.business.mycarenetcommons.mapper.v4

import org.taktik.connector.business.mycarenetdomaincommons.domain.CommonInput
import be.fgov.ehealth.mycarenet.commons.core.v4.CommonInputType
import be.fgov.ehealth.mycarenet.commons.core.v4.OriginType

object CommonInputMapper {
    fun mapCommonInputType(input: CommonInput): CommonInputType {
        return CommonInputType().apply {
            inputReference = input.inputReference
            origin = OriginType().apply {
                careProvider
            }
        }
    }
}
