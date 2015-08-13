# arya-metadata-generator

arya-metadata-generator provides a solution to automatically generate metadata from AgemUtils-based projects.

## How do I run it?

1. arya-metadata-generator expects AgemUtils-based project as a jar file located under `reference-project` folder. So, as a first step, we need to export our project as jar file and place it under `reference-project` folder.

2. Then we need to modify `config.properties` file. There, output files directory and reference project name must be defined.

## What's the meaning of these properties in `config.properties` file?

* `reference.project.name`: name of the jar file located under `reference-project` folder.
* `traverse.directories`: comma seperated jsp directories to be traversed in order to generate metadata files. (such as `WebRoot/WEB-INF/genel, WebRoot/WEB-INF/guvenlik` )
* `traverse.files.by.extension`: while traversing specified directories, these file extensions are used to generate metadata files. (such as `jsp,html`)
* `xml.files.directory`: output directory in which generated metadata files are placed.
* `generate.random.values`: if set to true, it also generates random values for metadata elements.

## TODO

* missing ZUL mandatory attribute?
* combobox options can be generated from `FormAttribute.selectBoxContentValues` annotation.
* pom.xml must be fixed to avoid re-defining `reference.project.name` property.