package doo.gl.autosns.spring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionConfirmation {

    @JsonProperty("Type")
    private String type;
    @JsonProperty("MessageId")
    private String messageId;
    @JsonProperty("Token")
    private String token;
    @JsonProperty("TopicArn")
    private String topicArn;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("SubscribeURL")
    private String subscribeUrl;
    @JsonProperty("Timestamp")
    private String timestamp;
    @JsonProperty("SignatureVersion")
    private String signatureVersion;
    @JsonProperty("Signature")
    private String signature;
    @JsonProperty("SigningCertURL")
    private String signingCertUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTopicArn() {
        return topicArn;
    }

    public void setTopicArn(String topicArn) {
        this.topicArn = topicArn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubscribeUrl() {
        return subscribeUrl;
    }

    public void setSubscribeUrl(String subscribeUrl) {
        this.subscribeUrl = subscribeUrl;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignatureVersion() {
        return signatureVersion;
    }

    public void setSignatureVersion(String signatureVersion) {
        this.signatureVersion = signatureVersion;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSigningCertUrl() {
        return signingCertUrl;
    }

    public void setSigningCertUrl(String signingCertUrl) {
        this.signingCertUrl = signingCertUrl;
    }

    @Override
    public String toString() {
        return "SubscriptionConfirmation{" +
            "type='" + type + '\'' +
            ", messageId='" + messageId + '\'' +
            ", token='" + token + '\'' +
            ", topicArn='" + topicArn + '\'' +
            ", message='" + message + '\'' +
            ", subscribeUrl='" + subscribeUrl + '\'' +
            ", timestamp='" + timestamp + '\'' +
            ", signatureVersion='" + signatureVersion + '\'' +
            ", signature='" + signature + '\'' +
            ", signingCertUrl='" + signingCertUrl + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SubscriptionConfirmation that = (SubscriptionConfirmation) o;
        return Objects.equals(type, that.type) &&
            Objects.equals(messageId, that.messageId) &&
            Objects.equals(token, that.token) &&
            Objects.equals(topicArn, that.topicArn) &&
            Objects.equals(message, that.message) &&
            Objects.equals(subscribeUrl, that.subscribeUrl) &&
            Objects.equals(timestamp, that.timestamp) &&
            Objects.equals(signatureVersion, that.signatureVersion) &&
            Objects.equals(signature, that.signature) &&
            Objects.equals(signingCertUrl, that.signingCertUrl);
    }

    @Override
    public int hashCode() {

        return Objects
            .hash(type, messageId, token, topicArn, message, subscribeUrl, timestamp, signatureVersion, signature, signingCertUrl);
    }
}
