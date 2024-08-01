/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author BINH
 */
public class ProviderDetail {
    private int Id;
    private String providerName;
    private String image;
    private String category;
    private String createAt;
    private String updatedAt;
    private int createdBy;
    private boolean isDeleted;
    private int deletedBy;
    private String deletedAt;
    private List<CardDetail> pricelist;


    public ProviderDetail() {
    }

    public ProviderDetail(int Id, String providerName) {
        this.Id = Id;
        this.providerName = providerName;
    }

    public ProviderDetail(int Id, String providerName, String image, String category, String createAt, String updatedAt, int createdBy, boolean isDeleted, int deletedBy, String deletedAt, List<CardDetail> pricelist) {
        this.Id = Id;
        this.providerName = providerName;
        this.image = image;
        this.category = category;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.isDeleted = isDeleted;
        this.deletedBy = deletedBy;
        this.deletedAt = deletedAt;
        this.pricelist = pricelist;
    }

 

        public List<CardDetail> getPricelist() {
        return pricelist;
    }

    public void setPricelist(List<CardDetail> pricelist) {
        this.pricelist = pricelist;
    }

    public int getId() {
        return Id;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public int getDeletedBy() {
        return deletedBy;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setDeletedBy(int deletedBy) {
        this.deletedBy = deletedBy;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "ProviderDetail{" + "Id=" + Id + ", providerName=" + providerName + ", image=" + image + ", category=" + category + ", createAt=" + createAt + ", updatedAt=" + updatedAt + ", createdBy=" + createdBy + ", isDeleted=" + isDeleted + ", deletedBy=" + deletedBy + ", deletedAt=" + deletedAt + ", pricelist=" + pricelist + '}';
    }
    

    
}