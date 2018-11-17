package cinema.backend.entities;

import cinema.backend.enums.AgeLimit;
import cinema.backend.enums.Sync;

/**
 *
 * @author CzPet
 */
public class Film {
  private long filmId;
  private String title;
  private String county;
  private Sync synconized;
  private String director;
  private String storyline;
  private int runtime;
  private int licenseToPlay;
  private AgeLimit ageLimit;
  private String posterPath;

  public long getFilmId() {
    return filmId;
  }

  public void setFilmId(long filmId) {
    this.filmId = filmId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCounty() {
    return county;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public Sync getSynconized() {
    return synconized;
  }

  public void setSynconized(Sync synconized) {
    this.synconized = synconized;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public String getStoryline() {
    return storyline;
  }

  public void setStoryline(String storyline) {
    this.storyline = storyline;
  }

  public int getRuntime() {
    return runtime;
  }

  public void setRuntime(int runtime) {
    this.runtime = runtime;
  }

  public int getLicenseToPlay() {
    return licenseToPlay;
  }

  public void setLicenseToPlay(int licenseToPlay) {
    this.licenseToPlay = licenseToPlay;
  }

  public AgeLimit getAgeLimit() {
    return ageLimit;
  }

  public void setAgeLimit(AgeLimit ageLimit) {
    this.ageLimit = ageLimit;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }
  
  public Object[] toArray() {
        String[] array = {Long.toString(filmId), title, synconized.name(), Integer.toString(runtime), ageLimit.name()};
        return array;
  }
}
