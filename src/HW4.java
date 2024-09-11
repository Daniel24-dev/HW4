import java.util.Random;

public class HW4 {
    public static int bossHealth = 650;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {270, 280, 250, 200};
    public static int[] heroesDamage = {20, 15, 10, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};
    public static int roundNumber = 0;
    public static int healMedic = 30;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()) {
            playRound();
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int health : heroesHealth) {
            if (health > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossAttack();
        heroesAttack();
        medicHeal();
        printStatistics();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length - 1);
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void bossAttack() {

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {

                heroesHealth[i] -= bossDamage;

                if (heroesHealth[i] < 0) {
                    heroesHealth[i] = 0;
                }
            }
        }

    }

    public static void heroesAttack() {
        Random random = new Random();
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (bossDefence.equals(heroesAttackType[i])) {
                    int coeff = random.nextInt(9) + 2; // 2 to 10
                    damage *= coeff;
                    System.out.println("Critical Damage: " + damage);
                }
                bossHealth -= damage;
                if (bossHealth < 0) {
                    bossHealth = 0;
                }
            }
        }
    }

    public static void medicHeal() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesAttackType[i].equals("Medic")) {
                continue;
            }
            if (heroesHealth[i] < 100) {
                if (heroesHealth[i]>0){
                heroesHealth[i] += healMedic;
                }
                System.out.println("medic use healing " + heroesHealth[i]);
                if (heroesHealth[i] > 270) {
                    heroesHealth[i] = 270;
                }

                return;
            }
        }
    }

    public static void printStatistics() {
        System.out.println("ROUND: " + roundNumber + " ------------------");
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage +
                " defence: " + (bossDefence == null ? "No defence" : bossDefence));

        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]
                    + " damage: " + heroesDamage[i]);
        }
    }
}
