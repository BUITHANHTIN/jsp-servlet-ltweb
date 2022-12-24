package com.laptrinhjavaweb.imp.service;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.DAO.IStatus;
import com.laptrinhjavaweb.model.Status;
import com.laptrinhjavaweb.service.IStatusService;

public class StatusService implements IStatusService {
	@Inject
	private IStatus status;

	@Override
	public List<Status> getAllStatusID() {

		return status.getAllStatusID();
	}

}
