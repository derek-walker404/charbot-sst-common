package co.charbot.sst.results;

import java.net.Socket;

import co.charbox.domain.model.SstResultsModel;

public interface SstResultsHandler {

	public boolean handle(SstResultsModel results, Socket client);
}
