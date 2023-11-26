package com.ryu.tobybe.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferwallDto {
    private long id;
    
    private String offerPublisherId;

    private String offerAppId;

    private String offerKey;

    private String offerPostbackUrl;

    private String adGetMediaCode;

    private String adGetMediaPostbackUrl;

    private String ayetPostbackUrl;
}
