package cinema.backend.enums;

/**
 *
 * @author CzPet
 */
public enum AgeLimit {     
  AGE_LIMIT_1,
  AGE_LIMIT_2, 
  AGE_LIMIT_3;
  
  
  public static String getAgeLimit(String s) {
    String limit;
    switch (s) {
      case "1":
        limit = "AGE_LIMIT_1";
        break;
      case "2":
        limit = "AGE_LIMIT_2";
        break;
      case "3":
        limit = "AGE_LIMIT_3";
        break;
      default:
        limit = "invalid age limit";
    }
    return limit;
  }
}