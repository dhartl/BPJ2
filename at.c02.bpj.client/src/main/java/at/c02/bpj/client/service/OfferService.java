package at.c02.bpj.client.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import at.c02.bpj.client.AuthContext;
import at.c02.bpj.client.api.OfferApi;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferPosition;
import at.c02.bpj.client.api.model.OfferStatus;

public class OfferService {

	public OfferApi offerApi;

	public OfferService(OfferApi offerApi) {
		super();
		this.offerApi = offerApi;
	}

	public List<Offer> getOffer() throws ServiceException {
		return Services.executeCall(offerApi.getOffer());
	}

	public boolean validateOffer(Offer offer) {
		if (offer.getOfferPositions().isEmpty()) {
			throw new ServiceException("Es muss mindestens eine Position zum Angebot hinzugefügt werden!");
		}
		for (OfferPosition op : offer.offerPositionsProperty()) {
			if (op.amountProperty().get() > 99 || op.amountProperty().get() < 1) {
				throw new ServiceException(
						"Die Menge der Position " + op.getPosNr() + " muss zwischen 1 und 99 liegen!");
			}
			if (op.priceProperty().get() > 1000000 || op.priceProperty().get() < 0) {
				throw new ServiceException(
						"Der Preis der Position " + op.getPosNr() + " muss zwischen 0 und 1000000 liegen!");
			}
		}
		return true;

	}

	public Offer createOffer(Offer offer) {
		validateOffer(offer);
		offer.setCreatedDt(new Date());
		offer.setStatus(OfferStatus.CREATED);
		offer.setEmployee(AuthContext.getInstance().getCurrentUser());
		return saveOffer(offer);
	}

	protected Offer saveOffer(Offer offer) throws ServiceException {
		return Services.executeCall(offerApi.saveOffer(offer));

	}

	public Offer completeOffer(Offer offer) {
		if (!OfferStatus.COMPLETED.equals(offer.getStatus())) {
			offer.setCompletedDt(new Date());
			offer.setStatus(OfferStatus.COMPLETED);
			return saveOffer(offer);
		}
		return offer;
	}

	public void exportOffer(File selectedFile, Offer offer) {
		completeOffer(offer);
		Document document = new Document();
		try (FileOutputStream fos = new FileOutputStream(selectedFile)) {
			PdfWriter.getInstance(document, fos);
			document.open();
			Font bold = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);

			Image img = Image.getInstance("AVl_Logo.jpg");
			img.setAbsolutePosition(428f, 765f);
			img.scaleToFit(150, 150);

			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");

			document.add(img);
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("Sehr geehrter Herr " + offer.getCustomer().getContactLastName() + ","));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("wie vereinbart finden Sie untenstehend unser Angebot"));
			document.add(new Paragraph("zu Ihrer Bestellung mit der Angebotsnummer " + offer.getOfferId().toString()
					+ ", erstellt am " + df.format(offer.getCreatedDt()) + ":"));
			document.add(new Paragraph("\n\n"));
			document.add(new Paragraph("Auftraggeber:", bold));
			document.add(new Paragraph(
					offer.getCustomer().getContactFirstName() + " " + offer.getCustomer().getContactLastName()));
			document.add(new Paragraph(offer.getCustomer().getCompanyName()));
			document.add(new Paragraph(offer.getCustomer().getStreet() + " " + offer.getCustomer().getHouseNr()));
			document.add(new Paragraph(offer.getCustomer().getPostCode() + " " + offer.getCustomer().getCity()));
			document.add(new Paragraph("\n\n"));

			document.add(new Paragraph("Auftragnehmer:", bold));
			document.add(new Paragraph(offer.getEmployee().getFirstname() + " " + offer.getEmployee().getLastname()));
			document.add(new Paragraph(offer.getEmployee().getEmail()));
			document.add(new Paragraph("\n\n"));
			DecimalFormat priceFormat = new DecimalFormat();
			priceFormat.setGroupingUsed(true);
			priceFormat.setMaximumFractionDigits(2);
			priceFormat.setMinimumFractionDigits(2);
			priceFormat.setGroupingSize(3);
			priceFormat.setDecimalSeparatorAlwaysShown(true);
			priceFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.GERMAN));

			int offerPositionCount = offer.getOfferPositions().size();
			if (offerPositionCount == 0) {
				document.add(new Paragraph("Das Angebot besitzt keine Positionen!"));
			} else {
				if (offerPositionCount == 1) {
					document.add(new Paragraph("Das Angebot setzt sich aus folgender Position zusammen:"));
				} else {

					document.add(new Paragraph("Das Angebot setzt sich aus folgenden Positionen zusammen:"));
				}

				document.add(new Paragraph("\n"));

				PdfPTable table = new PdfPTable(4);
				table.setHeaderRows(1);
				table.setTotalWidth(1000);
				table.setWidths(new float[] { 1, 5, 1.5f, 1.8f });
				table.addCell(new PdfPCell(new Phrase("PosNr", bold)));
				table.addCell(new PdfPCell(new Phrase("Artikel", bold)));
				table.addCell(new PdfPCell(new Phrase("Menge", bold)));
				table.addCell(new PdfPCell(new Phrase("Preis", bold)));
				for (OfferPosition position : offer.getOfferPositions()) {
					PdfPCell posCell = new PdfPCell(new Phrase(String.valueOf(position.getPosNr())));
					posCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					table.addCell(posCell);
					PdfPCell articleCell = new PdfPCell(new Phrase(position.getArticle().getName()));
					table.addCell(articleCell);
					PdfPCell amountCell = new PdfPCell(new Phrase(String.valueOf(position.getAmount())));
					amountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					table.addCell(amountCell);
					PdfPCell priceCell = new PdfPCell(new Phrase(priceFormat.format(position.getPrice())));
					priceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					table.addCell(priceCell);
				}
				document.add(table);
			}
			double gesamtpreis = offer.getOfferPositions().stream().mapToDouble(pos -> pos.getPrice() * pos.getAmount())
					.sum();
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("Wir erlauben uns, Ihnen für diese Bestellung " + priceFormat.format(gesamtpreis)
					+ " Euro in Rechnung zu stellen.", bold));
			document.add(new Paragraph("\n\n"));
			document.add(new Paragraph("Vielen Dank für Ihr Vertrauen!"));//
			document.add(new Paragraph("\n\n"));
			document.add(
					new Paragraph("Die Bestellung wurde abgeschlossen am " + df.format(offer.getCompletedDt()) + "."));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("Hochachtungsvoll,"));
			document.add(new Paragraph(offer.getEmployee().getFirstname() + " " + offer.getEmployee().getLastname()));
			document.close();

		} catch (IOException | DocumentException ex) {
			throw new ServiceException("Fehler beim Erzeugen der Exportdatei", ex);
		}
	}
}
