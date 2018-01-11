package server.plagiarism.dao;

/**
 * @author sinha.an
 * @version 1.0
 *
 * Interface
 * IDAOFactory: used to declare methods to instantiate DAO classes
 */
public interface IDAOFactory {
    // abstract method declaration for instantiating FileDAO
    FileDAO makeFileDAO();

    // abstract method declaration for instantiating ProjectDAO
    ProjectDAO makeProjectDAO();

    // abstract method declaration for instantiating ReportDAO
    ReportDAO makeReportDAO();
}
