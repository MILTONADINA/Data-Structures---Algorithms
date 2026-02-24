$srcDir = "d:\Data-Structures---Algorithms"
$javaFiles = Get-ChildItem -Path $srcDir -Filter *.java -Recurse

foreach ($file in $javaFiles) {
    # Figure out the package based on the path
    # e.g. d:\Data-Structures---Algorithms\avl\src\main\java\com\portfolio\dsa\avl\AVLTest.java
    # becomes com.portfolio.dsa.avl
    
    $path = $file.FullName
    if ($path -match "src\\main\\java\\(.*)\\") {
        $packagePath = $matches[1]
        $packageName = $packagePath -replace "\\", "."
        
        $content = Get-Content $path -Raw
        
        # Remove any existing package declaration
        $content = $content -replace '(?m)^package\s+[\w\.]+;\s*', ""
        
        # Add the new package declaration
        $newContent = "package $packageName;`r`n`r`n" + $content
        
        Set-Content -Path $path -Value $newContent -Encoding UTF8
    }
}
