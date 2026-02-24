$modules = @("core", "avl", "bst", "city-graph-navigator", "complexity", "graph", "hash-table", "postfix", "recursion", "doubly-linked-list")

foreach ($mod in $modules) {
    # Create directories
    $mainJava = Join-Path $mod "src\main\java\com\portfolio\dsa\$($mod.Replace('-',''))"
    $testJava = Join-Path $mod "src\test\java\com\portfolio\dsa\$($mod.Replace('-',''))"
    
    New-Item -ItemType Directory -Force -Path $mainJava | Out-Null
    New-Item -ItemType Directory -Force -Path $testJava | Out-Null

    # Create pom.xml
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
}

# Special handling for core, which others might depend on
# Add dependency to pom.xml for modules that need core (e.g., city-graph-navigator, graph, etc.)
# I will do this via separate tool calls.

# Now let's move files.
# AVL
Move-Item -Path "AVL\AVL\src\*" -Destination "avl\src\main\java\com\portfolio\dsa\avl" -Force
# BST
Move-Item -Path "BST_Assignment\BST_Assignment\src\*" -Destination "bst\src\main\java\com\portfolio\dsa\bst" -Force
# CityGraphNavigator
Move-Item -Path "CityGraphNavigator\CityGraphNavigator\src\*" -Destination "city-graph-navigator\src\main\java\com\portfolio\dsa\citygraphnavigator" -Force
# Complexity
Move-Item -Path "Complexity\Complexity\src\*" -Destination "complexity\src\main\java\com\portfolio\dsa\complexity" -Force
# Graph
Move-Item -Path "Graph\Graph\src\*" -Destination "graph\src\main\java\com\portfolio\dsa\graph" -Force
# HashTable
Move-Item -Path "HashTableAssignment\HashTableAssignment\src\*" -Destination "hash-table\src\main\java\com\portfolio\dsa\hashtable" -Force
# Postfix
Move-Item -Path "Postfix\Postfix\src\*" -Destination "postfix\src\main\java\com\portfolio\dsa\postfix" -Force
# Recursion
Move-Item -Path "Recursion\Recursion\src\*" -Destination "recursion\src\main\java\com\portfolio\dsa\recursion" -Force
# Doubly linked list (it has two, let's just combine them or use DoublyLinkeLlists)
Move-Item -Path "DoublyLinkeLlists\DoublyLinkeLlists\src\*" -Destination "doubly-linked-list\src\main\java\com\portfolio\dsa\doublylinkedlist" -Force
Move-Item -Path "doublylinked\doublylinked\*.java" -Destination "doubly-linked-list\src\main\java\com\portfolio\dsa\doublylinkedlist" -Force

# Clean up old folders
$oldDirs = @("AVL", "BST_Assignment", "CityGraphNavigator", "Complexity", "DoublyLinkeLlists", "Graph", "HashTableAssignment", "Postfix", "Recursion", "doublylinked", "bin")
foreach ($dir in $oldDirs) {
    if (Test-Path $dir) { Remove-Item -Recurse -Force $dir }
}
