package at.c02.bpj.server.entity;

public enum OfferStatus {
	/**
	 * Angebot wurde gerade erstellt
	 */
	CREATED,
	/**
	 * Angebot wird gerade verarbeitet
	 */
	PROGRESS,
	/**
	 * Angebot wurde abgeschlossen
	 */
	COMPLETED;
}
