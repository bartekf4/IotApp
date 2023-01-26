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

/** This is an auto generated class representing the UserDevice type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "UserDevices", type = Model.Type.USER, version = 1)
public final class UserDevice implements Model {
  public static final QueryField ID = field("UserDevice", "id");
  public static final QueryField ID_USER = field("UserDevice", "idUser");
  public static final QueryField ID_DEVICE = field("UserDevice", "idDevice");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String idUser;
  private final @ModelField(targetType="ID", isRequired = true) String idDevice;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getIdUser() {
      return idUser;
  }
  
  public String getIdDevice() {
      return idDevice;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private UserDevice(String id, String idUser, String idDevice) {
    this.id = id;
    this.idUser = idUser;
    this.idDevice = idDevice;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      UserDevice userDevice = (UserDevice) obj;
      return ObjectsCompat.equals(getId(), userDevice.getId()) &&
              ObjectsCompat.equals(getIdUser(), userDevice.getIdUser()) &&
              ObjectsCompat.equals(getIdDevice(), userDevice.getIdDevice()) &&
              ObjectsCompat.equals(getCreatedAt(), userDevice.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), userDevice.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getIdUser())
      .append(getIdDevice())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("UserDevice {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("idUser=" + String.valueOf(getIdUser()) + ", ")
      .append("idDevice=" + String.valueOf(getIdDevice()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static IdUserStep builder() {
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
  public static UserDevice justId(String id) {
    return new UserDevice(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      idUser,
      idDevice);
  }
  public interface IdUserStep {
    IdDeviceStep idUser(String idUser);
  }
  

  public interface IdDeviceStep {
    BuildStep idDevice(String idDevice);
  }
  

  public interface BuildStep {
    UserDevice build();
    BuildStep id(String id);
  }
  

  public static class Builder implements IdUserStep, IdDeviceStep, BuildStep {
    private String id;
    private String idUser;
    private String idDevice;
    @Override
     public UserDevice build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new UserDevice(
          id,
          idUser,
          idDevice);
    }
    
    @Override
     public IdDeviceStep idUser(String idUser) {
        Objects.requireNonNull(idUser);
        this.idUser = idUser;
        return this;
    }
    
    @Override
     public BuildStep idDevice(String idDevice) {
        Objects.requireNonNull(idDevice);
        this.idDevice = idDevice;
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
    private CopyOfBuilder(String id, String idUser, String idDevice) {
      super.id(id);
      super.idUser(idUser)
        .idDevice(idDevice);
    }
    
    @Override
     public CopyOfBuilder idUser(String idUser) {
      return (CopyOfBuilder) super.idUser(idUser);
    }
    
    @Override
     public CopyOfBuilder idDevice(String idDevice) {
      return (CopyOfBuilder) super.idDevice(idDevice);
    }
  }
  
}
