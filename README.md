# minecraft-plugin-teleporter

# TODO

rechts klick auf einen hebel 
    if (ist teil von einem stargate) {
        if (stargate aktiv) {
            if (ausgehendes stargate) {
                verbindung schliesen
                beide stargates auf ursprung zurücksetzen
            } else {
                nix passiert
            }    
        } else {    
            überprüfe ausgewähltes ziel
            starte animation an beiden gates
            wenn jemand ins stargate geht wird er geportet
            nach 38 sekunden schliesen sich die stargates
        }
    } else {
        if (erforderlichen blöcke für ein stargate sind da) {
            if (spieler ist op admin) {
                deklariere stargate
            }
        }
    }
