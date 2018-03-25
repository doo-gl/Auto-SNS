package doo.gl.autosns.spring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionNotification {

    @JsonProperty("Type")
    private String type;
    @JsonProperty("MessageId")
    private String messageId;
    @JsonProperty("TopicArn")
    private String topicArn;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("Timestamp")
    private String timestamp;
    @JsonProperty("SignatureVersion")
    private String signatureVersion;
    @JsonProperty("Signature")
    private String signature;
    @JsonProperty("SigningCertURL")
    private String signingCertUrl;
    @JsonProperty("UnsubscribeURL")
    private String unsubscribeUrl;

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

    public String getUnsubscribeUrl() {
        return unsubscribeUrl;
    }

    public void setUnsubscribeUrl(String unsubscribeUrl) {
        this.unsubscribeUrl = unsubscribeUrl;
    }

    @Override
    public String toString() {
        return "SubscriptionNotification{" +
            "type='" + type + '\'' +
            ", messageId='" + messageId + '\'' +
            ", topicArn='" + topicArn + '\'' +
            ", message='" + message + '\'' +
            ", timestamp='" + timestamp + '\'' +
            ", signatureVersion='" + signatureVersion + '\'' +
            ", signature='" + signature + '\'' +
            ", signingCertUrl='" + signingCertUrl + '\'' +
            ", unsubscribeUrl='" + unsubscribeUrl + '\'' +
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
        SubscriptionNotification that = (SubscriptionNotification) o;
        return Objects.equals(type, that.type) &&
            Objects.equals(messageId, that.messageId) &&
            Objects.equals(topicArn, that.topicArn) &&
            Objects.equals(message, that.message) &&
            Objects.equals(timestamp, that.timestamp) &&
            Objects.equals(signatureVersion, that.signatureVersion) &&
            Objects.equals(signature, that.signature) &&
            Objects.equals(signingCertUrl, that.signingCertUrl) &&
            Objects.equals(unsubscribeUrl, that.unsubscribeUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, messageId, topicArn, message, timestamp, signatureVersion, signature, signingCertUrl, unsubscribeUrl);
    }
}
