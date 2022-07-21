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
$drives | Select-Object -Property name, $property, $propertyFree, $propertyUsed
$drives | Select-Object -property name, @{n='totalSpaceGB';e={($_.used + $_.free) / 1GB}}

$data = @(
    @{name='a'}
    @{name='c'}
    @{name='e'}
    @{name='f'}
    @{name='d'}
    @{name='b'}
)

$d = $data | Sort-Object -Property @{e={$_.name}}

$CIMParams = @{
    ClassName = 'Win32_Bios'
    ComputerName = $ComputerName
}

if($Credential)
{
    $CIMParams.Credential = $Credential
}

Get-CIMInstance @CIMParams

$log = @{Path = '.\logfile.log'}
Add-Content "logging this command" @log

$robo = @{R=1;W=1;MT=8}
robocopy source destination @robo

$people = @{
    Kevin = @{
        age  = 36
        city = 'Austin'
    }
    Alex = @{
        age  = 9
        city = 'Austin'
    }
}

foreach($name in $people.keys)
{
    $person = $people[$name]
    '{0}, age {1}, is in {2}' -f $name, $person.age, $person.city
}