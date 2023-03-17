package co.mlforex.forecast.vinculadorCodigo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.util.UUID;

@DynamoDBTable(tableName = "VinculadorCodigoInfo")
public class TransaccionInfo implements Serializable {

    private String UID;
    @DynamoDBAttribute
    private String nombreApp;
    @DynamoDBAttribute
    private String version;

    @JsonIgnore
    private String idTransaccion;

    //Attributes
    private Mensaje mensaje;

    public TransaccionInfo(){

    }
    public TransaccionInfo(String nombreApp, String version){
        this.nombreApp = nombreApp;
        this.version = version;
        this.UID = DigestUtils.md5Hex(nombreApp.toLowerCase()+":"+version);
    }


    public String getNombreApp() {
        return nombreApp;
    }

    public void setNombreApp(String nombreApp) {
        this.nombreApp = nombreApp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @DynamoDBAttribute(attributeName = "mensaje")
    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    @DynamoDBHashKey(attributeName = "UID")
    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String generateUID(){
        return DigestUtils.md5Hex(nombreApp.toLowerCase()+":"+version);
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }
}
