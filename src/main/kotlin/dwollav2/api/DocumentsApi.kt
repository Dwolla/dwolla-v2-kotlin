package dwollav2.api

import com.github.kittinunf.fuel.core.FileDataPart
import com.github.kittinunf.fuel.core.InlineDataPart
import dwollav2.Client
import dwollav2.api.documents.DocumentType
import dwollav2.exception.DwollaException
import dwollav2.exception.OAuthException
import dwollav2.http.MultipartBody
import dwollav2.resource.documents.Document
import dwollav2.resource.documents.Documents
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
