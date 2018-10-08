package de.mathar.simulations;

import de.mathar.simulations.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins="*")
public class Controller {


    private boolean initialized = false;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping(value = "/init")
    public boolean init() {
        unitRepository.deleteAll();
        playerRepository.deleteAll();
        setUpStaticValuesToDatabase();
        return true;
    }

    @RequestMapping(method = RequestMethod.GET, value="/attacker")
    public Player attacker () {
        return playerRepository.findByAttackerIs(true);
    }

    @RequestMapping(method = RequestMethod.GET,value="/attacker/units")
    public Map<UnitName, Integer> attackerUnits(@RequestParam(value="category", required = false, defaultValue = "-1") int category) {
        Map<UnitName, Integer> units = playerRepository.findByAttackerIs(true).getUnits();
        if (category >= 0) {
            Map<UnitName, Integer> unitsInCategory = new HashMap<>();
            for(Map.Entry<UnitName, Integer> unit : units.entrySet()) {
                if (unitRepository.findByName(unit.getKey()).getAttack() == category) {
                    unitsInCategory.put(unit.getKey(), unit.getValue());
                }
            }
            units = unitsInCategory;
        }
        return units;
    }

    @RequestMapping(method = RequestMethod.GET, value="/defender")
    public Player defender() {
        return playerRepository.findByAttackerIs(false);
    }

    @RequestMapping(method = RequestMethod.GET, value="/defender/units")
    public Map<UnitName, Integer> defenderUnits() {
        return playerRepository.findByAttackerIs(false).getUnits();
    }

    @RequestMapping(method = RequestMethod.GET, value="/unit")
    public Unit unit(@RequestParam(value="name") String name) {
        return unitRepository.findByName(UnitName.valueOf(name.toUpperCase()));
    }


    private void setUpStaticValuesToDatabase() {
        setUpUnitRepository();
        setUpPlayerRepository();
    }

    private void setUpPlayerRepository() {
        setUpAttacker();
        setUpDefender();
    }

    private void setUpDefender() {
        Map<UnitName, Integer> defenderUnits = new HashMap<>();
        defenderUnits.put(UnitName.TANK, 5);
        defenderUnits.put(UnitName.INFANTRY, 1);
        defenderUnits.put(UnitName.FIGHTER, 2);
        Player defender = new Player(defenderUnits, false);
        playerRepository.save(defender);
    }

    private void setUpAttacker() {
        Map<UnitName, Integer> attackerUnits = new HashMap<>();
        attackerUnits.put(UnitName.INFANTRY, 15);
        attackerUnits.put(UnitName.BOMBER, 2);
        Player attacker = new Player(attackerUnits, true);
        playerRepository.save(attacker);
    }

    private void setUpUnitRepository() {
        unitRepository.save(new Unit(UnitName.INFANTRY, 3, 1, 2));
        unitRepository.save(new Unit(UnitName.ARTILLERY, 4, 2,2));
        unitRepository.save(new Unit(UnitName.TANK, 5, 3,3));
        unitRepository.save(new Unit(UnitName.AAA, 6, 0,1));

        unitRepository.save(new Unit(UnitName.FIGHTER, 10, 3, 4));
        unitRepository.save(new Unit(UnitName.BOMBER, 12, 4, 1));

        unitRepository.save(new Unit(UnitName.SUBMARINE, 6, 2,1));
        unitRepository.save(new Unit(UnitName.TRANSPORT, 7, 0, 0));
        unitRepository.save(new Unit(UnitName.DESTORYER, 8, 2,2));
        unitRepository.save(new Unit(UnitName.CRUISER, 12, 3,3));
        unitRepository.save(new Unit(UnitName.AIRCRAFT_CARRIER, 14, 1, 2));
        unitRepository.save(new Unit(UnitName.BATTLESHIP, 20, 4,4));

    }
}
