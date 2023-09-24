package com.protonmail.landrevillejf.featuretoggle.api;

import com.protonmail.landrevillejf.featuretoggle.config.Api;
import com.protonmail.landrevillejf.featuretoggle.entity.FeatureToggle;
import com.protonmail.landrevillejf.featuretoggle.entity.dto.FeatureToggleDto;
import com.protonmail.landrevillejf.featuretoggle.exception.ApiExceptionEnums;
import com.protonmail.landrevillejf.featuretoggle.exception.common.CommonApiException;
import com.protonmail.landrevillejf.featuretoggle.service.common.ICommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Feature Toggle", description = "Feature Toggle management APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(Api.FEATURE)
@RestController
@RequiredArgsConstructor
public class FeatureToggleController {

    Logger logger = LoggerFactory.getLogger(FeatureToggleController.class);
    private final ICommonService<FeatureToggle> iCommonService;
    private final ModelMapper modelMapper;

    //region Get FeatureToggle
    @Operation(summary = "Retrieve all FeatureToggle", tags = { "FeatureToggle", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = FeatureToggle.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no FeatureToggle", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FeatureToggle>> getAllFeatures (
            @RequestParam(value = "page", defaultValue = "1",required = false) int page,
            @RequestParam(value = "limit", defaultValue = "15" ,required = false) int limit) throws Exception{
        List<FeatureToggle> featureToggleList= iCommonService.findAll(page, limit);
        if (featureToggleList.isEmpty()){
            logger.error(ApiExceptionEnums.EMPTY_LIST.name());
            throw new CommonApiException(ApiExceptionEnums.EMPTY_LIST.name());
        }
        return new ResponseEntity<>(featureToggleList, HttpStatus.OK);
    }

    @Operation(summary = "Get a FeatureToggle by its Uid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the FeatureToggle",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FeatureToggle.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid uid supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "FeatureToggle not found", content = @Content)}) // @formatter:on
    @GetMapping(Api.FEATURE_BY_FEATURE_UID)
    public ResponseEntity<FeatureToggle> getFeatureToggleByUid(@PathVariable("uid") String uid) throws Exception{
        FeatureToggle featureToggle = iCommonService.findByUid(uid);
        if (featureToggle !=null) {
            return new ResponseEntity<>(featureToggle, HttpStatus.OK);
        } else {
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonApiException(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
    }
    //endregion

    //region Save FeatureToggle
    @Operation(summary = "Create a new FeatureToggle", tags = { "FeatureToggle", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = FeatureToggle.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
            )
    public ResponseEntity<FeatureToggle> createFeatureToggle(@RequestBody FeatureToggleDto dto)throws Exception{
        FeatureToggle featureToggle = modelMapper.map(dto, FeatureToggle.class);
        if(featureToggle.getName().isEmpty()){
            logger.error(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
            throw new CommonApiException(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
        }
        FeatureToggle newFeatureToggle= iCommonService.save(featureToggle);
        return new ResponseEntity<>(newFeatureToggle,HttpStatus.CREATED);
    }
    //endregion

    //region Update FeatureToggle
    @Operation(summary = "Update a FeatureToggle by Uid", tags = { "FeatureToggle", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = FeatureToggle.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping(
            path =Api.FEATURE_BY_FEATURE_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<FeatureToggle> updateFeatureToggle(@RequestBody FeatureToggleDto dto,@PathVariable("uid") String uid)throws Exception{
        FeatureToggle featureToggle = modelMapper.map(dto, FeatureToggle.class);
        if(featureToggle.getName().isEmpty()){
            logger.error(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
            throw new CommonApiException(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
        }
        if(uid.isEmpty()){
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonApiException(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }

        FeatureToggle newFeatureToggle= iCommonService.update(featureToggle,uid);
        return new ResponseEntity<>(newFeatureToggle,HttpStatus.CREATED);
    }
    //endregion

    //region Delete FeatureToggle By Uid
    @Operation(summary = "Delete a FeatureToggle by Uid", tags = { "FeatureToggle", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(
            path =Api.FEATURE_BY_FEATURE_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<FeatureToggle> deleteFeatureToggle(@PathVariable("uid") String uid)throws Exception{

        if(uid.isEmpty()){
            logger.error(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
            throw new CommonApiException(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
        }

        iCommonService.deleteByUid(uid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endregion

    //region Delete FeatureToggle All
    @Operation(summary = "Delete all FeatureToggle", tags = { "FeatureToggle", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<FeatureToggle> deleteAllFeatureToggle()throws Exception{

        iCommonService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    //endregion
}
