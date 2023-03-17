package co.mlforex.forecast.vinculadorCodigo.repository;

import co.mlforex.forecast.vinculadorCodigo.model.TransaccionInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface VincularCodigoRepo extends CrudRepository<TransaccionInfo, String> {


}
