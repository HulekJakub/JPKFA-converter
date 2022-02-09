1. Sposób uruchomienia programu
    Należy zbudować projekt i będąc w folderze xml_parser/xml_parser-generator/target wpisać w terminalu:
        java -jar Generator.jar classes\faktury-sprzedazowe-test.xlsx classes\from-xlsx.xml
    lub
        java -jar Generator.jar classes\faktury-sprzedazowe-test.csv classes\from-csv.xml
    dla plików na stronie. Ewentualnie można:
        java -jar Generator.jar relativePathToInputFile relativePathToOutputFile
    w przypadku użycia pliku jar poza środowiskiem.



