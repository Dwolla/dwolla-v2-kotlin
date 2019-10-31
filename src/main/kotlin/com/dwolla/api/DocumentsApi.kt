package com.dwolla.api

import com.dwolla.DwollaClient
import com.dwolla.api.documents.DocumentType
import com.dwolla.exception.DwollaApiException
import com.dwolla.exception.DwollaAuthException
import com.dwolla.http.MultipartBody
import com.dwolla.resource.documents.Document
import com.dwolla.resource.documents.Documents
import com.dwolla.util.Paths.Companion.BENEFICIAL_OWNERS
import com.dwolla.util.Paths.Companion.CUSTOMERS
import com.dwolla.util.Paths.Companion.DOCUMENTS
import com.github.kittinunf.fuel.core.FileDataPart
import com.github.kittinunf.fuel.core.InlineDataPart
import java.io.File

class DocumentsApi(private val client: DwollaClient) {

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun createForCustomer(customerId: String, documentType: DocumentType, file: File): Document {
        return client.postFollow(
            Document::class.java,
            customerDocumentsUrl(customerId),
            createDocumentBody(documentType, file)
        ).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun createForBeneficialOwner(beneficialOwnerId: String, documentType: DocumentType, file: File): Document {
        return client.postFollow(
            Document::class.java,
            beneficialOwnerDocumentsUrl(beneficialOwnerId),
            createDocumentBody(documentType, file)
        ).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun get(id: String): Document {
        return client.get(Document::class.java, documentUrl(id)).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun listByCustomer(customerId: String): Documents {
        return client.get(Documents::class.java, customerDocumentsUrl(customerId)).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun listByBeneficialOwner(beneficialOwnerId: String): Documents {
        return client.get(Documents::class.java, beneficialOwnerDocumentsUrl(beneficialOwnerId)).body
    }

    private fun documentUrl(id: String): String {
        return client.urlBuilder.buildUrl(DOCUMENTS, id)
    }

    private fun customerDocumentsUrl(customerId: String): String {
        return client.urlBuilder.buildUrl(CUSTOMERS, customerId, DOCUMENTS)
    }

    private fun beneficialOwnerDocumentsUrl(customerId: String): String {
        return client.urlBuilder.buildUrl(BENEFICIAL_OWNERS, customerId, DOCUMENTS)
    }

    private fun createDocumentBody(documentType: DocumentType, file: File): MultipartBody {
        return MultipartBody(
            InlineDataPart(documentType.value, "documentType"),
            FileDataPart(file, "file")
        )
    }
}
