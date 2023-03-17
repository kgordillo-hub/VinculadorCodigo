package co.mlforex.forecast.vinculadorCodigo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

import java.io.Serializable;

@DynamoDBDocument
public class Mensaje implements Serializable {

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute
    private Boolean esPublico;
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute
    private Boolean codigoVulnerable;
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute
    private Boolean openAPIFileCorrect;
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute
    private Boolean imagenGenerada;
    @DynamoDBAttribute
    private String linkRepo;
    @DynamoDBAttribute
    private String branchRepoName;
    @DynamoDBAttribute
    private String idUsuario;

    public Mensaje(){}

    public Boolean getEsPublico() {
        return esPublico;
    }

    public void setEsPublico(Boolean esPublico) {
        this.esPublico = esPublico;
    }

    public Boolean getCodigoVulnerable() {
        return codigoVulnerable;
    }

    public void setCodigoVulnerable(Boolean codigoVulnerable) {
        this.codigoVulnerable = codigoVulnerable;
    }

    public Boolean getOpenAPIFileCorrect() {
        return openAPIFileCorrect;
    }

    public void setOpenAPIFileCorrect(Boolean openAPIFileCorrect) {
        this.openAPIFileCorrect = openAPIFileCorrect;
    }

    public Boolean getImagenGenerada() {
        return imagenGenerada;
    }

    public void setImagenGenerada(Boolean imagenGenerada) {
        this.imagenGenerada = imagenGenerada;
    }

    public String getLinkRepo() {
        return linkRepo;
    }

    public void setLinkRepo(String linkRepo) {
        this.linkRepo = linkRepo;
    }

    public String getBranchRepoName() {
        return branchRepoName;
    }

    public void setBranchRepoName(String branchRepoName) {
        this.branchRepoName = branchRepoName;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
