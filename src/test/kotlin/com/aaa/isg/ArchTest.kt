package com.aaa.isg

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.jupiter.api.Test

class ArchTest {

    @Test
    fun servicesAndRepositoriesShouldNotDependOnWebLayer() {

        val importedClasses = ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.aaa.isg")

        noClasses()
            .that()
                .resideInAnyPackage("com.aaa.isg.service..")
            .or()
                .resideInAnyPackage("com.aaa.isg.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.aaa.isg.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses)
    }
}
