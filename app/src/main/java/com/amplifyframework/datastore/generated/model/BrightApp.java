package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the BrightApp type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "BrightApps", type = Model.Type.USER, version = 1)
public final class BrightApp implements Model {
  public static final QueryField ID = field("BrightApp", "id");
  public static final QueryField TIMESTAMP = field("BrightApp", "timestamp");
  public static final QueryField PAYLOAD = field("BrightApp", "payload");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String timestamp;
  private final @ModelField(targetType="String") String payload;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getTimestamp() {
      return timestamp;
  }
  
  public String getPayload() {
      return payload;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private BrightApp(String id, String timestamp, String payload) {
    this.id = id;
    this.timestamp = timestamp;
    this.payload = payload;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      BrightApp brightApp = (BrightApp) obj;
      return ObjectsCompat.equals(getId(), brightApp.getId()) &&
              ObjectsCompat.equals(getTimestamp(), brightApp.getTimestamp()) &&
              ObjectsCompat.equals(getPayload(), brightApp.getPayload()) &&
              ObjectsCompat.equals(getCreatedAt(), brightApp.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), brightApp.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTimestamp())
      .append(getPayload())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("BrightApp {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("timestamp=" + String.valueOf(getTimestamp()) + ", ")
      .append("payload=" + String.valueOf(getPayload()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TimestampStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static BrightApp justId(String id) {
    return new BrightApp(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      timestamp,
      payload);
  }
  public interface TimestampStep {
    BuildStep timestamp(String timestamp);
  }
  

  public interface BuildStep {
    BrightApp build();
    BuildStep id(String id);
    BuildStep payload(String payload);
  }
  

  public static class Builder implements TimestampStep, BuildStep {
    private String id;
    private String timestamp;
    private String payload;
    @Override
     public BrightApp build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new BrightApp(
          id,
          timestamp,
          payload);
    }
    
    @Override
     public BuildStep timestamp(String timestamp) {
        Objects.requireNonNull(timestamp);
        this.timestamp = timestamp;
        return this;
    }
    
    @Override
     public BuildStep payload(String payload) {
        this.payload = payload;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String timestamp, String payload) {
      super.id(id);
      super.timestamp(timestamp)
        .payload(payload);
    }
    
    @Override
     public CopyOfBuilder timestamp(String timestamp) {
      return (CopyOfBuilder) super.timestamp(timestamp);
    }
    
    @Override
     public CopyOfBuilder payload(String payload) {
      return (CopyOfBuilder) super.payload(payload);
    }
  }
  
}
