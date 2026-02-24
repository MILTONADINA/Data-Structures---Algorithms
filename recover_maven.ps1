$zipsToRecover = @(
    @{ Zip = "AVL"; Mod = "avl" },
    @{ Zip = "Complexity"; Mod = "complexity" },
    @{ Zip = "Graph"; Mod = "graph" },
    @{ Zip = "Postfix"; Mod = "postfix" },
    @{ Zip = "Recursion"; Mod = "recursion" }
)

foreach ($item in $zipsToRecover) {
    # Extract Zip
    Expand-Archive -Path "$($item.Zip).zip" -DestinationPath "$($item.Zip)_extracted" -Force
    
    # Recreate module dirs
    $mod = $item.Mod
    $mainJava = Join-Path $mod "src\main\java\com\portfolio\dsa\$($mod.Replace('-',''))"
    $testJava = Join-Path $mod "src\test\java\com\portfolio\dsa\$($mod.Replace('-',''))"
    
    New-Item -ItemType Directory -Force -Path $mainJava | Out-Null
    New-Item -ItemType Directory -Force -Path $testJava | Out-Null

    # Recreate pom.xml
    $pomPath = Join-Path $mod "pom.xml"
    $pomContent = @"
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.portfolio.dsa</groupId>
        <artifactId>dsa-portfolio-parent</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <artifactId>$mod</artifactId>
    <name>DSA :: $mod</name>
</project>
"@
    Set-Content -Path $pomPath -Value $pomContent

    # Move files
    $srcPathPattern = Join-Path "$($item.Zip)_extracted" "$($item.Zip)\src\*"
    Copy-Item -Path $srcPathPattern -Destination $mainJava -Recurse -Force
}

# Clean temporary extraction dirs safely
foreach ($item in $zipsToRecover) {
    Remove-Item -Path "$($item.Zip)_extracted" -Recurse -Force
}
