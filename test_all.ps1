$projects = @(
    @{ Name="AVL"; Dir="AVL\AVL"; Src="src\*.java"; Entry="AVLTest" },
    @{ Name="BST_Assignment"; Dir="BST_Assignment\BST_Assignment"; Src="src\*.java"; Entry="Test" },
    @{ Name="CityGraphNavigator"; Dir="CityGraphNavigator\CityGraphNavigator"; Src="src\*.java"; Entry="DAGTest" },
    @{ Name="Complexity"; Dir="Complexity\Complexity"; Src="src\*.java"; Entry="ComplexityAssignment" },
    @{ Name="DoublyLinkeLlists"; Dir="DoublyLinkeLlists\DoublyLinkeLlists"; Src="src\*.java"; Entry="DoublyLinkedListTest" },
    @{ Name="Graph"; Dir="Graph\Graph"; Src="src\*.java"; Entry="DAGTEST" },
    @{ Name="HashTableAssignment"; Dir="HashTableAssignment\HashTableAssignment"; Src="src\**\*.java"; Entry="hashtableapp.Test" },
    @{ Name="Postfix"; Dir="Postfix\Postfix"; Src="src\*.java"; Entry="PostfixTest" },
    @{ Name="Recursion"; Dir="Recursion\Recursion"; Src="src\*.java"; Entry="RecursionAssignment" },
    @{ Name="doublylinked"; Dir="doublylinked\doublylinked"; Src="*.java"; Entry="DoublyLinkedListTest" }
)

$report = @()

foreach ($proj in $projects) {
    Write-Host "Evaluating $($proj.Name)..."
    $fullDir = Join-Path "d:\Data-Structures---Algorithms" $proj.Dir
    
    # Clean/Create bin directory
    $binDir = Join-Path $fullDir "bin"
    if (Test-Path $binDir) { Remove-Item -Recurse -Force $binDir }
    New-Item -ItemType Directory -Path $binDir | Out-Null
    
    # Find sources
    $srcFiles = Get-ChildItem -Path $fullDir -Filter *.java -Recurse | Select-Object -ExpandProperty FullName
    
    $compileStart = Get-Date
    # We use Measure-Command to get runtime, but for javac we can just call it
    if ($srcFiles.Count -gt 0) {
        # Create a file with all java files
        $sourcesPath = Join-Path $fullDir "sources.txt"
        $srcFiles | Out-File -FilePath $sourcesPath -Encoding ASCII
        
        $compileProc = Start-Process -FilePath "javac" -ArgumentList "-d", "bin", "@sources.txt" -WorkingDirectory $fullDir -NoNewWindow -Wait -PassThru
        
        if ($compileProc.ExitCode -eq 0) {
            $compileStatus = "PASS"
            
            # Now run it
            $entry = $proj.Entry
            $runProc = Start-Process -FilePath "java" -ArgumentList "-cp", "bin", $entry -WorkingDirectory $fullDir -NoNewWindow -Wait -PassThru
            
            if ($runProc.ExitCode -eq 0) {
                $runStatus = "PASS"
            } else {
                $runStatus = "FAIL"
            }
        } else {
            $compileStatus = "FAIL"
            $runStatus = "SKIP"
        }
    } else {
        $compileStatus = "FAIL (No sources found)"
        $runStatus = "SKIP"
    }

    $report += [PSCustomObject]@{
        Project = $proj.Name
        Compile = $compileStatus
        Execute = $runStatus
    }
}

$report | Format-Table -AutoSize
