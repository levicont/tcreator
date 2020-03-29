package com.lvg.tcreator.utils;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.NdtQualificationLevel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Victor Levchenko LVG Corp. on 26.03.2020.
 */
public class NameUtils {

    public static List<String> parseFIO(String source)throws IllegalArgumentException{
        checkNullEmptyString(source);
        List<String> result = Arrays.asList(source.trim().split(" "));
        if (result.size() != 3)
            throw new IllegalArgumentException("Size of source not equal 3 but "+result.size());
        result.stream().forEach(str -> {
            if (str.trim().isEmpty()) throw new IllegalArgumentException("Name or SecondName or Surname is empty");
        });

        return result;
    }

    //Parse String source e.g. MT-III or mt-II or Mt-I or Mt-III and take method from it
    public static NdtMethod parseNdtMethod(String source) throws IllegalArgumentException{
       checkNullEmptyString(source);
        String strResult = source.trim().substring(0,1).toUpperCase();
        Optional<NdtMethod> optionalNdtMethod = Arrays.stream(NdtMethod.values()).filter(ndtMethod -> ndtMethod.toString().
                equals(strResult)).findFirst();
        if (!optionalNdtMethod.isPresent()) throw new IllegalArgumentException("Source "+source+" is wrong NDT method");
        return optionalNdtMethod.get();
    }

    //Parse String source e.g. MT-III or mt-II or Mt-I or Mt-III and take qualification level from it
    public static NdtQualificationLevel parseNdtQualificationLevel(String source) throws IllegalArgumentException{
        checkNullEmptyString(source);
        String strResult = source.trim().substring(2).toUpperCase();
        Optional<NdtQualificationLevel> optionalNdtQualificationLevel =
                Arrays.stream(NdtQualificationLevel.values()).filter(ndtQualificationLevel -> ndtQualificationLevel.toString().
                equals(strResult)).findFirst();
        if (!optionalNdtQualificationLevel.isPresent()) throw new IllegalArgumentException("Source "+source+" is wrong NDT qualification level");
        return optionalNdtQualificationLevel.get();
    }

    private static void checkNullEmptyString(String source)throws IllegalArgumentException{
        if (source == null || source.trim().isEmpty())
            throw new IllegalArgumentException("Source cannot be null or empty");
    }
}
