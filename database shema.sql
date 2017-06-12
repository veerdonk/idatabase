CREATE TABLE `idatabase`.`de_genes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `stress_factor` VARCHAR(45) NOT NULL,
  `timepoint` VARCHAR(45) NOT NULL,
  `ens_id` VARCHAR(45) NOT NULL,
  `log2fold` DOUBLE NOT NULL,
  `pval` DOUBLE NOT NULL,
  `gene_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `gene_name` (`gene_name` ASC),
  INDEX `ens_id` (`ens_id` ASC));
