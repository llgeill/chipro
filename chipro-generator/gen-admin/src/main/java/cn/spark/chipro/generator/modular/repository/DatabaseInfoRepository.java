package cn.spark.chipro.generator.modular.repository;

import cn.spark.chipro.generator.modular.model.DatabaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by stephan on 20.03.16.
 */
public interface DatabaseInfoRepository extends JpaRepository<DatabaseInfo, Long> {
}
