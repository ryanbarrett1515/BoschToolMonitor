package org.cofc.bosch.ToolMonitor.components;

import org.springframework.web.multipart.MultipartFile;

public class WorkPieceCarrier {
    private String author;
    private String revisionNumber;
    private String revisionReason;
    private String fileType;

    private MultipartFile[] componentsList;
    private String workPieceCarrierNumber;
    private String valueStream;
    private String productionLine;
    private String productType;

    private MultipartFile[] drawingLibrary;

    private String reasonForChange;
    private String reasonCategory;
    private boolean toolLifeAchieved;
    private String locationRepairTicket;
    private String downTimeImpact;

    private MultipartFile[] PDFs;
    private MultipartFile[] CADs;
    private MultipartFile JPEG;

    public String getValueStream() {
        return valueStream;
    }

    public void setValueStream(String valueStream) {
        this.valueStream = valueStream;
    }

    public String getProductionLine() {
        return productionLine;
    }

    public void setProductionLine(String productionLine) {
        this.productionLine = productionLine;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRevisionNumber() {
        return revisionNumber;
    }

    public void setRevisionNumber(String revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public String getRevisionReason() {
        return revisionReason;
    }

    public void setRevisionReason(String revisionReason) {
        this.revisionReason = revisionReason;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public MultipartFile[] getComponentsList() {
        return componentsList;
    }

    public void setComponentsList(MultipartFile[] componentsList) {
        this.componentsList = componentsList;
    }

    public String getWorkPieceCarrierNumber() {
        return workPieceCarrierNumber;
    }

    public void setWorkPieceCarrierNumber(String workPieceCarrierNumber) {
        this.workPieceCarrierNumber = workPieceCarrierNumber;
    }

    public MultipartFile[] getDrawingLibrary() {
        return drawingLibrary;
    }

    public void setDrawingLibrary(MultipartFile[] drawingLibrary) {
        this.drawingLibrary = drawingLibrary;
    }

    public String getReasonForChange() {
        return reasonForChange;
    }

    public void setReasonForChange(String reasonForChange) {
        this.reasonForChange = reasonForChange;
    }

    public String getReasonCategory() {
        return reasonCategory;
    }

    public void setReasonCategory(String reasonCategory) {
        this.reasonCategory = reasonCategory;
    }

    public boolean isToolLifeAchieved() {
        return toolLifeAchieved;
    }

    public void setToolLifeAchieved(boolean toolLifeAchieved) {
        this.toolLifeAchieved = toolLifeAchieved;
    }

    public String getLocationRepairTicket() {
        return locationRepairTicket;
    }

    public void setLocationRepairTicket(String locationRepairTicket) {
        this.locationRepairTicket = locationRepairTicket;
    }

    public String getDownTimeImpact() {
        return downTimeImpact;
    }

    public void setDownTimeImpact(String downTimeImpact) {
        this.downTimeImpact = downTimeImpact;
    }

    public MultipartFile[] getPDFs() {
        return PDFs;
    }

    public void setPDFs(MultipartFile[] PDFs) {
        this.PDFs = PDFs;
    }

    public MultipartFile[] getCADs() {
        return CADs;
    }

    public void setCADs(MultipartFile[] CADs) {
        this.CADs = CADs;
    }

    public MultipartFile getJPEG() {
        return JPEG;
    }

    public void setJPEG(MultipartFile JPEG) {
        this.JPEG = JPEG;
    }
}