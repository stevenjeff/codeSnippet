$property = @{
    name = 'totalSpaceGB'
    expression = { ($_.used + $_.free) / 1GB }
}
$propertyFree = @{
    name = 'totalSpaceGBFree'
    expression = { ($_.free) / 1GB }
}
$propertyUsed = @{
    name = 'totalSpaceGBUsed'
    expression = { ($_.used) / 1GB }
}

$drives = Get-PSDrive | Where Used
$drives | Select-Object -Property name, $property