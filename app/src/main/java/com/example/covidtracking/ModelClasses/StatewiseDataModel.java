package com.example.covidtracking.ModelClasses;


import java.util.Collection;
import java.util.List;

public class StatewiseDataModel {


    private boolean success;
    private DataDTO data;
    private String lastRefreshed;
    private String lastOriginUpdate;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public String getLastRefreshed() {
        return lastRefreshed;
    }

    public void setLastRefreshed(String lastRefreshed) {
        this.lastRefreshed = lastRefreshed;
    }

    public String getLastOriginUpdate() {
        return lastOriginUpdate;
    }

    public void setLastOriginUpdate(String lastOriginUpdate) {
        this.lastOriginUpdate = lastOriginUpdate;
    }

    public static class DataDTO {

        private List<RegionalDTO> regional;


        public List<RegionalDTO> getRegional() {
            return regional;
        }

        public void setRegional(List<RegionalDTO> regional) {
            this.regional = regional;
        }


        public static class RegionalDTO {
            private String loc;
            private int confirmedCasesIndian;
            private int confirmedCasesForeign;
            private int discharged;
            private int deaths;
            private int totalConfirmed;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public int getConfirmedCasesIndian() {
                return confirmedCasesIndian;
            }

            public void setConfirmedCasesIndian(int confirmedCasesIndian) {
                this.confirmedCasesIndian = confirmedCasesIndian;
            }

            public int getConfirmedCasesForeign() {
                return confirmedCasesForeign;
            }

            public void setConfirmedCasesForeign(int confirmedCasesForeign) {
                this.confirmedCasesForeign = confirmedCasesForeign;
            }

            public int getDischarged() {
                return discharged;
            }

            public void setDischarged(int discharged) {
                this.discharged = discharged;
            }

            public int getDeaths() {
                return deaths;
            }

            public void setDeaths(int deaths) {
                this.deaths = deaths;
            }

            public int getTotalConfirmed() {
                return totalConfirmed;
            }

            public void setTotalConfirmed(int totalConfirmed) {
                this.totalConfirmed = totalConfirmed;
            }
        }
    }
}
