package com.example.pocketpolitics.model;

import java.util.Date;
import java.util.List;

public class Article extends LikeableItem {
	
	private int traffnummer;
	private String datum;
	private String id;
	private String titel;
	private String rm;
	private String relaterat_id;
	private String beteckning;
	private double score;
	private String notisrubrik;
	private String notis;
	private String beslutsdag;
	private int beslutad;
	
	public Article(String content, Date date, boolean isHidden, int opinion,
			int nbrOfLikes, int nbrOfDislikes, List<Comment> replies, 
			int traffnummer,
			String datum,
			String id,
			String titel,
			String rm,
			String relaterat_id,
			String beteckning,
			double score,
			String notisrubrik,
			String notis,
			String beslutsdag,
			int beslutad) {
		
		super(content, date, isHidden, opinion, nbrOfLikes, nbrOfDislikes, replies);
		
		this.traffnummer = traffnummer;
		this.datum = datum;
		this.id = id;
		this.titel = titel;
		this.rm = rm;
		this.relaterat_id =relaterat_id;
		this.beteckning = beteckning;
		this.score=score;
		this.notisrubrik=notisrubrik;
		this.notis=notis;
		this.beslutsdag=beslutsdag;
		this.beslutad=beslutad;
	}
	
	// Temporary constructor
	public Article() {
		super();
	}
	
	public int getTraffnummer() {
		return traffnummer;
	}

	public void setTraffnummer(int traffnummer) {
		this.traffnummer = traffnummer;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getRm() {
		return rm;
	}

	public void setRm(String rm) {
		this.rm = rm;
	}

	public String getRelaterat_id() {
		return relaterat_id;
	}

	public void setRelaterat_id(String relaterat_id) {
		this.relaterat_id = relaterat_id;
	}

	public String getBeteckning() {
		return beteckning;
	}

	public void setBeteckning(String beteckning) {
		this.beteckning = beteckning;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getNotisrubrik() {
		return notisrubrik;
	}

	public void setNotisrubrik(String notisrubrik) {
		this.notisrubrik = notisrubrik;
	}

	public String getNotis() {
		return notis;
	}

	public void setNotis(String notis) {
		this.notis = notis;
	}

	public String getBeslutsdag() {
		return beslutsdag;
	}

	public void setBeslutsdag(String beslutsdag) {
		this.beslutsdag = beslutsdag;
	}

	public int getBeslutad() {
		return beslutad;
	}

	public void setBeslutad(int beslutad) {
		this.beslutad = beslutad;
	}

}
