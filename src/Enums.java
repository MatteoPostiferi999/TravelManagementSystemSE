public class Enums {

    // Enum per le preferenze generali del viaggio
    public enum Preference {
        ADVENTURE, RELAX, CULTURE
    }

    // Enum per le abilità richieste per le guide
    public enum Skill {
        CULTURAL,
        ADVENTURE,
        NATURE,
        HISTORICAL,
        LANGUAGE
    }

    // Enum per lo stato del pagamento
    public enum PaymentStatus {
        PENDING, COMPLETED, CANCELLED
    }

    // Enum per il tipo di viaggio, considerando età, budget e durata
    public enum TripType {
        SHORT_TRIP,    // Viaggi brevi (es. weekend)
        LONG_TRIP,     // Viaggi lunghi (es. vacanze di più settimane)
        BUDGET_TRIP,   // Viaggi economici
        LUXURY_TRIP,   // Viaggi di lusso
        ADVENTURE_TRIP,// Viaggi di avventura
        FAMILY_FRIENDLY, // Viaggi per famiglie
        SOLO_TRIP,   // Viaggi per viaggiatori solitari
        CULTURAL
    }
}