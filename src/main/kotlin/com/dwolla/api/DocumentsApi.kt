package com.dwolla.api

import com.dwolla.Dwolla
import com.dwolla.api.documents.DocumentType
import com.dwolla.exception.DwollaException
import com.dwolla.exception.OAuthException
import com.dwolla.http.MultipartBody
import com.dwolla.resource.documents.Document
import com.dwolla.resource.documents.Documents
import com.dwolla.util.Paths.Companion.BENEFICIAL_OWNERS
import com.dwolla.util.Paths.Companion.CUSTOMERS
import com.dwolla.util.Paths.Companion.DOCUMENTS
import com.github.kittinunf.fuel.core.FileDataPart
import com.github.kittinunf.fuel.core.InlineDataPart
import java.io.File

class DocumentsApi(private val dwolla: Dwolla) {

    @Throws(DwollaException::class, OAuthException::class)
    fun createForCustomer(customerId: String, documentType: DocumentType, file: File): Document {
        return dwolla.postFollow(
            Document::class.java,
            customerDocumentsUrl(customerId),
            createDocumentBody(documentType, file)
        ).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun createForBeneficialOwner(beneficialOwnerId: String, documentType: DocumentType, file: File): Document {
        return dwolla.postFollow(
            Document::class.java,
            beneficialOwnerDocumentsUrl(beneficialOwnerId),
            createDocumentBody(documentType, file)
        ).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun get(id: String): Document {
        return dwolla.get(Document::class.java, documentUrl(id)).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun listByCustomer(customerId: String): Documents {
        return dwolla.get(Documents::class.java, customerDocumentsUrl(customerId)).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun listByBeneficialOwner(beneficialOwnerId: String): Documents {
        return dwolla.get(Documents::class.java, beneficialOwnerDocumentsUrl(beneficialOwnerId)).body
    }

    private fun documentUrl(id: String): String {
        return dwolla.urlBuilder.buildUrl(DOCUMENTS, id)
    }

    private fun customerDocumentsUrl(customerId: String): String {
        return dwolla.urlBuilder.buildUrl(CUSTOMERS, customerId, DOCUMENTS)
    }

    private fun beneficialOwnerDocumentsUrl(customerId: String): String {
        return dwolla.urlBuilder.buildUrl(BENEFICIAL_OWNERS, customerId, DOCUMENTS)
    }

    private fun createDocumentBody(documentType: DocumentType, file: File): MultipartBody {
        return MultipartBody(
            InlineDataPart(documentType.value, "documentType"),
            FileDataPart(file, "file")
        )
    }
}
