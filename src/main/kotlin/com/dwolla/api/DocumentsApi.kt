package com.dwolla.api

import com.github.kittinunf.fuel.core.FileDataPart
import com.github.kittinunf.fuel.core.InlineDataPart
import com.dwolla.Client
import com.dwolla.api.documents.DocumentType
import com.dwolla.exception.DwollaException
import com.dwolla.exception.OAuthException
import com.dwolla.http.MultipartBody
import com.dwolla.resource.documents.Document
import com.dwolla.resource.documents.Documents
import java.io.File

class DocumentsApi(private val client: Client) {

    @Throws(DwollaException::class, OAuthException::class)
    fun createForCustomer(id: String, documentType: DocumentType, file: File): Document {
        val createDocument = client.post(
            client.urlBuilder.buildUrl("customers", id, "documents"),
                MultipartBody(
                        InlineDataPart(documentType.value, "documentType"),
                        FileDataPart(file, "file")
                )
        )
        val url = createDocument.headers.get("location")!!
        return client.get(Document::class.java, url).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun getById(id: String): Document {
        val url = client.urlBuilder.buildUrl("documents", id)
        return client.get(Document::class.java, url).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun listByCustomer(id: String): Documents {
        val url = client.urlBuilder.buildUrl("customers", id, "documents")
        return client.get(Documents::class.java, url).body
    }
}
