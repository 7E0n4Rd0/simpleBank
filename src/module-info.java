module simpleBank {
	exports model.util;
	exports application;
	exports model.excpetion;
	exports model.services;
	exports model.dao;
	exports model.dao.impl;
	exports db;
	exports model.entities;

	requires java.sql;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	
	opens application to javafx.controls,javafx.graphics,javafx.fxml;
}