/**
 * 
 */
package edu.drexel.cs.cs576.mwa29;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mark
 *
 */
public class KwicGeneratorTest {

	private static final String TEST_RESOURCE_BASE_PATH = "src/test/resources/";

	@Test
	public void generateKwicIndex_successfully() throws FileNotFoundException {
		final KwicEntry[] expectedResults = new KwicEntry[] {
				new KwicEntry("this", "", "is a good input file"),
				new KwicEntry("is", "this", "a good input file"),
				new KwicEntry("a", "this is", "good input file"),
				new KwicEntry("good", "this is a", "input file"),
				new KwicEntry("input", "this is a good", "file"),
				new KwicEntry("file", "this is a good input", "") };

		final String inputFile = TEST_RESOURCE_BASE_PATH + "inputfile.txt";
		final String stopWordFile = TEST_RESOURCE_BASE_PATH + "stopwords.txt";
		final KwicGenerator kwicGenerator = new KwicGenerator(inputFile,
				stopWordFile);
		final KwicIndex kwicIndex = kwicGenerator.generateKwicIndex();
		final List<KwicEntry> index = kwicIndex.getIndex();

		Assert.assertArrayEquals(expectedResults,
				index.toArray(new KwicEntry[] {}));
	}

	@Test
	public void generateKwicIndex_withStopwords_successfully()
			throws FileNotFoundException {
		final KwicEntry[] expectedResults = new KwicEntry[] {
				new KwicEntry("good", "this is a", "input file"),
				new KwicEntry("input", "this is a good", "file"),
				new KwicEntry("file", "this is a good input", "") };

		final String inputFile = TEST_RESOURCE_BASE_PATH + "inputfile.txt";
		final String stopWordFile = TEST_RESOURCE_BASE_PATH
				+ "valid_stopwords.txt";
		final KwicGenerator kwicGenerator = new KwicGenerator(inputFile,
				stopWordFile);
		final KwicIndex kwicIndex = kwicGenerator.generateKwicIndex();
		final List<KwicEntry> index = kwicIndex.getIndex();

		Assert.assertArrayEquals(expectedResults,
				index.toArray(new KwicEntry[] {}));
	}

	@Test
	public void generateKwicIndex_longLines_successfully()
			throws FileNotFoundException {
		final KwicEntry[] expectedResults = new KwicEntry[] {
				new KwicEntry(
						"abridging",
						"... establishment of religion or prohibiting the free exercise thereof or",
						"the freedom of speech or of the press or the ..."),
				new KwicEntry(
						"according",
						"... otherwise re-examined in any Court of the United States than",
						"to the rules of the common law"),
				new KwicEntry("accusation",
						"... to be informed of the nature and cause of the",
						"to be confronted with the witnesses against him to have ..."),
				new KwicEntry("accused", "In all criminal prosecutions the",
						"shall enjoy the right to a speedy and public trial ..."),
				new KwicEntry("actual",
						"... land or naval forces or in the Militia when in",
						"service in time of War or public danger nor shall ..."),
				new KwicEntry(
						"affirmation",
						"... shall issue but upon probable cause supported by Oath or",
						"and particularly describing the place to be searched and the ..."),
				new KwicEntry("answer", "No person shall be held to",
						"for a capital or otherwise infamous crime unless on a ..."),
				new KwicEntry(
						"arising",
						"... presentment or indictment of a Grand Jury except in cases",
						"in the land or naval forces or in the Militia ..."),
				new KwicEntry("Arms",
						"... State the right of the people to keep and bear",
						"shall not be infringed"),
				new KwicEntry(
						"ascertained",
						"... shall have been committed which district shall have been previously",
						"by law and to be informed of the nature and ..."),
				new KwicEntry(
						"assemble",
						"... the press or the right of the people peaceably to",
						"and to petition the Government for a redress of grievances"),
				new KwicEntry(
						"Assistance",
						"... for obtaining witnesses in his favor and to have the",
						"of Counsel for his defence"),
				new KwicEntry("bail", "Excessive",
						"shall not be required nor excessive fines imposed nor cruel ..."),
				new KwicEntry("bear",
						"... free State the right of the people to keep and",
						"Arms shall not be infringed"),
				new KwicEntry("capital",
						"No person shall be held to answer for a",
						"or otherwise infamous crime unless on a presentment or indictment ..."),
				new KwicEntry(
						"case",
						"... life or limb nor shall be compelled in any criminal",
						"to be a witness against himself nor be deprived of ..."),
				new KwicEntry(
						"cases",
						"... a presentment or indictment of a Grand Jury except in",
						"arising in the land or naval forces or in the ..."),
				new KwicEntry(
						"cause",
						"... be violated and no Warrants shall issue but upon probable",
						"supported by Oath or affirmation and particularly describing the place ..."),
				new KwicEntry("cause",
						"... by law and to be informed of the nature and",
						"of the accusation to be confronted with the witnesses against ..."),
				new KwicEntry("certain",
						"The enumeration in the Constitution of",
						"rights shall not be construed to deny or disparage others ..."),
				new KwicEntry(
						"committed",
						"... the State and district wherein the crime shall have been",
						"which district shall have been previously ascertained by law and ..."),
				new KwicEntry("common", "In Suits at",
						"law where the value in controversy shall exceed twenty dollars ..."),
				new KwicEntry(
						"common",
						"... the United States than according to the rules of the",
						"law"),
				new KwicEntry("compelled",
						"... put in jeopardy of life or limb nor shall be",
						"in any criminal case to be a witness against himself ..."),
				new KwicEntry(
						"compensation",
						"... shall private property be taken for public use without just",
						""),
				new KwicEntry(
						"compulsory",
						"... to be confronted with the witnesses against him to have",
						"process for obtaining witnesses in his favor and to have ..."),
				new KwicEntry("confronted",
						"... of the nature and cause of the accusation to be",
						"with the witnesses against him to have compulsory process for ..."),
				new KwicEntry("Congress", "",
						"shall make no law respecting an establishment of religion or ..."),
				new KwicEntry(
						"consent",
						"... time of peace be quartered in any house without the",
						"of the Owner nor in time of war but in ..."),
				new KwicEntry("Constitution", "The enumeration in the",
						"of certain rights shall not be construed to deny or ..."),
				new KwicEntry("Constitution",
						"The powers not delegated to the United States by the",
						"nor prohibited by it to the States are reserved to ..."),
				new KwicEntry(
						"construed",
						"... enumeration in the Constitution of certain rights shall not be",
						"to deny or disparage others retained by the people"),
				new KwicEntry("controversy",
						"In Suits at common law where the value in",
						"shall exceed twenty dollars the right of trial by jury ..."),
				new KwicEntry(
						"Counsel",
						"... witnesses in his favor and to have the Assistance of",
						"for his defence"),
				new KwicEntry(
						"Court",
						"... tried by a jury shall be otherwise re-examined in any",
						"of the United States than according to the rules of ..."),
				new KwicEntry(
						"crime",
						"... be held to answer for a capital or otherwise infamous",
						"unless on a presentment or indictment of a Grand Jury ..."),
				new KwicEntry(
						"crime",
						"... an impartial jury of the State and district wherein the",
						"shall have been committed which district shall have been previously ..."),
				new KwicEntry("criminal",
						"... of life or limb nor shall be compelled in any",
						"case to be a witness against himself nor be deprived ..."),
				new KwicEntry("criminal", "In all",
						"prosecutions the accused shall enjoy the right to a speedy ..."),
				new KwicEntry(
						"cruel",
						"... bail shall not be required nor excessive fines imposed nor",
						"and unusual punishments inflicted"),
				new KwicEntry("danger",
						"... when in actual service in time of War or public",
						"nor shall any person be subject for the same offence ..."),
				new KwicEntry(
						"defence",
						"... favor and to have the Assistance of Counsel for his",
						""),
				new KwicEntry("delegated", "The powers not",
						"to the United States by the Constitution nor prohibited by ..."),
				new KwicEntry(
						"deny",
						"... the Constitution of certain rights shall not be construed to",
						"or disparage others retained by the people"),
				new KwicEntry(
						"deprived",
						"... criminal case to be a witness against himself nor be",
						"of life liberty or property without due process of law ..."),
				new KwicEntry(
						"describing",
						"... upon probable cause supported by Oath or affirmation and particularly",
						"the place to be searched and the persons or things ..."),
				new KwicEntry(
						"disparage",
						"... of certain rights shall not be construed to deny or",
						"others retained by the people"),
				new KwicEntry(
						"district",
						"... public trial by an impartial jury of the State and",
						"wherein the crime shall have been committed which district shall ..."),
				new KwicEntry(
						"district",
						"... and district wherein the crime shall have been committed which",
						"shall have been previously ascertained by law and to be ..."),
				new KwicEntry(
						"dollars",
						"... common law where the value in controversy shall exceed twenty",
						"the right of trial by jury shall be preserved and ..."),
				new KwicEntry(
						"effects",
						"... people to be secure in their persons houses papers and",
						"against unreasonable searches and seizures shall not be violated and ..."),
				new KwicEntry("enjoy",
						"In all criminal prosecutions the accused shall",
						"the right to a speedy and public trial by an ..."),
				new KwicEntry("enumeration", "The",
						"in the Constitution of certain rights shall not be construed ..."),
				new KwicEntry("establishment",
						"Congress shall make no law respecting an",
						"of religion or prohibiting the free exercise thereof or abridging ..."),
				new KwicEntry(
						"exceed",
						"... Suits at common law where the value in controversy shall",
						"twenty dollars the right of trial by jury shall be ..."),
				new KwicEntry("Excessive", "",
						"bail shall not be required nor excessive fines imposed nor ..."),
				new KwicEntry("excessive",
						"Excessive bail shall not be required nor",
						"fines imposed nor cruel and unusual punishments inflicted"),
				new KwicEntry(
						"exercise",
						"... law respecting an establishment of religion or prohibiting the free",
						"thereof or abridging the freedom of speech or of the ..."),
				new KwicEntry("fact",
						"... right of trial by jury shall be preserved and no",
						"tried by a jury shall be otherwise re-examined in any ..."),
				new KwicEntry(
						"favor",
						"... him to have compulsory process for obtaining witnesses in his",
						"and to have the Assistance of Counsel for his defence"),
				new KwicEntry("fines",
						"Excessive bail shall not be required nor excessive",
						"imposed nor cruel and unusual punishments inflicted"),
				new KwicEntry(
						"forces",
						"... Jury except in cases arising in the land or naval",
						"or in the Militia when in actual service in time ..."),
				new KwicEntry(
						"free",
						"... no law respecting an establishment of religion or prohibiting the",
						"exercise thereof or abridging the freedom of speech or of ..."),
				new KwicEntry(
						"free",
						"... well regulated Militia being necessary to the security of a",
						"State the right of the people to keep and bear ..."),
				new KwicEntry(
						"freedom",
						"... religion or prohibiting the free exercise thereof or abridging the",
						"of speech or of the press or the right of ..."),
				new KwicEntry(
						"Government",
						"... of the people peaceably to assemble and to petition the",
						"for a redress of grievances"),
				new KwicEntry(
						"Grand",
						"... infamous crime unless on a presentment or indictment of a",
						"Jury except in cases arising in the land or naval ..."),
				new KwicEntry(
						"grievances",
						"... assemble and to petition the Government for a redress of",
						""),
				new KwicEntry("held", "No person shall be",
						"to answer for a capital or otherwise infamous crime unless ..."),
				new KwicEntry(
						"himself",
						"... compelled in any criminal case to be a witness against",
						"nor be deprived of life liberty or property without due ..."),
				new KwicEntry(
						"house",
						"... Soldier shall in time of peace be quartered in any",
						"without the consent of the Owner nor in time of ..."),
				new KwicEntry(
						"houses",
						"... right of the people to be secure in their persons",
						"papers and effects against unreasonable searches and seizures shall not ..."),
				new KwicEntry("impartial",
						"... the right to a speedy and public trial by an",
						"jury of the State and district wherein the crime shall ..."),
				new KwicEntry(
						"imposed",
						"Excessive bail shall not be required nor excessive fines",
						"nor cruel and unusual punishments inflicted"),
				new KwicEntry(
						"indictment",
						"... capital or otherwise infamous crime unless on a presentment or",
						"of a Grand Jury except in cases arising in the ..."),
				new KwicEntry(
						"infamous",
						"... shall be held to answer for a capital or otherwise",
						"crime unless on a presentment or indictment of a Grand ..."),
				new KwicEntry(
						"inflicted",
						"... required nor excessive fines imposed nor cruel and unusual punishments",
						""),
				new KwicEntry(
						"informed",
						"... shall have been previously ascertained by law and to be",
						"of the nature and cause of the accusation to be ..."),
				new KwicEntry("infringed",
						"... the people to keep and bear Arms shall not be", ""),
				new KwicEntry(
						"issue",
						"... and seizures shall not be violated and no Warrants shall",
						"but upon probable cause supported by Oath or affirmation and ..."),
				new KwicEntry("jeopardy",
						"... subject for the same offence to be twice put in",
						"of life or limb nor shall be compelled in any ..."),
				new KwicEntry(
						"Jury",
						"... crime unless on a presentment or indictment of a Grand",
						"except in cases arising in the land or naval forces ..."),
				new KwicEntry(
						"jury",
						"... right to a speedy and public trial by an impartial",
						"of the State and district wherein the crime shall have ..."),
				new KwicEntry(
						"jury",
						"... controversy shall exceed twenty dollars the right of trial by",
						"shall be preserved and no fact tried by a jury ..."),
				new KwicEntry("jury",
						"... jury shall be preserved and no fact tried by a",
						"shall be otherwise re-examined in any Court of the United ..."),
				new KwicEntry(
						"just",
						"... nor shall private property be taken for public use without",
						"compensation"),
				new KwicEntry("land",
						"... of a Grand Jury except in cases arising in the",
						"or naval forces or in the Militia when in actual ..."),
				new KwicEntry("law", "Congress shall make no",
						"respecting an establishment of religion or prohibiting the free exercise ..."),
				new KwicEntry("law",
						"... of war but in a manner to be prescribed by", ""),
				new KwicEntry(
						"law",
						"... deprived of life liberty or property without due process of",
						"nor shall private property be taken for public use without ..."),
				new KwicEntry(
						"law",
						"... been committed which district shall have been previously ascertained by",
						"and to be informed of the nature and cause of ..."),
				new KwicEntry("law", "In Suits at common",
						"where the value in controversy shall exceed twenty dollars the ..."),
				new KwicEntry(
						"law",
						"... United States than according to the rules of the common",
						""),
				new KwicEntry(
						"liberty",
						"... be a witness against himself nor be deprived of life",
						"or property without due process of law nor shall private ..."),
				new KwicEntry("life",
						"... the same offence to be twice put in jeopardy of",
						"or limb nor shall be compelled in any criminal case ..."),
				new KwicEntry(
						"life",
						"... to be a witness against himself nor be deprived of",
						"liberty or property without due process of law nor shall ..."),
				new KwicEntry("limb",
						"... offence to be twice put in jeopardy of life or",
						"nor shall be compelled in any criminal case to be ..."),
				new KwicEntry("make", "Congress shall",
						"no law respecting an establishment of religion or prohibiting the ..."),
				new KwicEntry("manner",
						"... the Owner nor in time of war but in a",
						"to be prescribed by law"),
				new KwicEntry("Militia", "A well regulated",
						"being necessary to the security of a free State the ..."),
				new KwicEntry("Militia",
						"... arising in the land or naval forces or in the",
						"when in actual service in time of War or public ..."),
				new KwicEntry(
						"nature",
						"... previously ascertained by law and to be informed of the",
						"and cause of the accusation to be confronted with the ..."),
				new KwicEntry(
						"naval",
						"... Grand Jury except in cases arising in the land or",
						"forces or in the Militia when in actual service in ..."),
				new KwicEntry("necessary", "A well regulated Militia being",
						"to the security of a free State the right of ..."),
				new KwicEntry(
						"Oath",
						"... no Warrants shall issue but upon probable cause supported by",
						"or affirmation and particularly describing the place to be searched ..."),
				new KwicEntry(
						"obtaining",
						"... with the witnesses against him to have compulsory process for",
						"witnesses in his favor and to have the Assistance of ..."),
				new KwicEntry(
						"offence",
						"... danger nor shall any person be subject for the same",
						"to be twice put in jeopardy of life or limb ..."),
				new KwicEntry(
						"Owner",
						"... be quartered in any house without the consent of the",
						"nor in time of war but in a manner to ..."),
				new KwicEntry(
						"papers",
						"... of the people to be secure in their persons houses",
						"and effects against unreasonable searches and seizures shall not be ..."),
				new KwicEntry(
						"particularly",
						"... but upon probable cause supported by Oath or affirmation and",
						"describing the place to be searched and the persons or ..."),
				new KwicEntry("peace", "No Soldier shall in time of",
						"be quartered in any house without the consent of the ..."),
				new KwicEntry("peaceably",
						"... or of the press or the right of the people",
						"to assemble and to petition the Government for a redress ..."),
				new KwicEntry("people",
						"... speech or of the press or the right of the",
						"peaceably to assemble and to petition the Government for a ..."),
				new KwicEntry("people",
						"... the security of a free State the right of the",
						"to keep and bear Arms shall not be infringed"),
				new KwicEntry("people", "The right of the",
						"to be secure in their persons houses papers and effects ..."),
				new KwicEntry(
						"people",
						"... be construed to deny or disparage others retained by the",
						""),
				new KwicEntry(
						"people",
						"... States are reserved to the States respectively or to the",
						""),
				new KwicEntry("person", "No",
						"shall be held to answer for a capital or otherwise ..."),
				new KwicEntry("person",
						"... in time of War or public danger nor shall any",
						"be subject for the same offence to be twice put ..."),
				new KwicEntry(
						"persons",
						"The right of the people to be secure in their",
						"houses papers and effects against unreasonable searches and seizures shall ..."),
				new KwicEntry(
						"persons",
						"... and particularly describing the place to be searched and the",
						"or things to be seized"),
				new KwicEntry(
						"petition",
						"... the right of the people peaceably to assemble and to",
						"the Government for a redress of grievances"),
				new KwicEntry(
						"place",
						"... cause supported by Oath or affirmation and particularly describing the",
						"to be searched and the persons or things to be ..."),
				new KwicEntry("powers", "The",
						"not delegated to the United States by the Constitution nor ..."),
				new KwicEntry("prescribed",
						"... in time of war but in a manner to be", "by law"),
				new KwicEntry(
						"presentment",
						"... for a capital or otherwise infamous crime unless on a",
						"or indictment of a Grand Jury except in cases arising ..."),
				new KwicEntry(
						"preserved",
						"... twenty dollars the right of trial by jury shall be",
						"and no fact tried by a jury shall be otherwise ..."),
				new KwicEntry(
						"press",
						"... thereof or abridging the freedom of speech or of the",
						"or the right of the people peaceably to assemble and ..."),
				new KwicEntry(
						"previously",
						"... crime shall have been committed which district shall have been",
						"ascertained by law and to be informed of the nature ..."),
				new KwicEntry(
						"private",
						"... liberty or property without due process of law nor shall",
						"property be taken for public use without just compensation"),
				new KwicEntry(
						"probable",
						"... not be violated and no Warrants shall issue but upon",
						"cause supported by Oath or affirmation and particularly describing the ..."),
				new KwicEntry(
						"process",
						"... nor be deprived of life liberty or property without due",
						"of law nor shall private property be taken for public ..."),
				new KwicEntry(
						"process",
						"... be confronted with the witnesses against him to have compulsory",
						"for obtaining witnesses in his favor and to have the ..."),
				new KwicEntry(
						"prohibited",
						"... not delegated to the United States by the Constitution nor",
						"by it to the States are reserved to the States ..."),
				new KwicEntry(
						"prohibiting",
						"... shall make no law respecting an establishment of religion or",
						"the free exercise thereof or abridging the freedom of speech ..."),
				new KwicEntry(
						"property",
						"... witness against himself nor be deprived of life liberty or",
						"without due process of law nor shall private property be ..."),
				new KwicEntry(
						"property",
						"... or property without due process of law nor shall private",
						"be taken for public use without just compensation"),
				new KwicEntry("prosecutions", "In all criminal",
						"the accused shall enjoy the right to a speedy and ..."),
				new KwicEntry("public",
						"... Militia when in actual service in time of War or",
						"danger nor shall any person be subject for the same ..."),
				new KwicEntry(
						"public",
						"... process of law nor shall private property be taken for",
						"use without just compensation"),
				new KwicEntry(
						"public",
						"... the accused shall enjoy the right to a speedy and",
						"trial by an impartial jury of the State and district ..."),
				new KwicEntry(
						"punishments",
						"... be required nor excessive fines imposed nor cruel and unusual",
						"inflicted"),
				new KwicEntry("quartered",
						"No Soldier shall in time of peace be",
						"in any house without the consent of the Owner nor ..."),
				new KwicEntry("re-examined",
						"... and no fact tried by a jury shall be otherwise",
						"in any Court of the United States than according to ..."),
				new KwicEntry(
						"redress",
						"... peaceably to assemble and to petition the Government for a",
						"of grievances"),
				new KwicEntry("regulated", "A well",
						"Militia being necessary to the security of a free State ..."),
				new KwicEntry(
						"religion",
						"Congress shall make no law respecting an establishment of",
						"or prohibiting the free exercise thereof or abridging the freedom ..."),
				new KwicEntry("required", "Excessive bail shall not be",
						"nor excessive fines imposed nor cruel and unusual punishments inflicted"),
				new KwicEntry(
						"reserved",
						"... the Constitution nor prohibited by it to the States are",
						"to the States respectively or to the people"),
				new KwicEntry("respecting", "Congress shall make no law",
						"an establishment of religion or prohibiting the free exercise thereof ..."),
				new KwicEntry("respectively",
						"... by it to the States are reserved to the States",
						"or to the people"),
				new KwicEntry(
						"retained",
						"... rights shall not be construed to deny or disparage others",
						"by the people"),
				new KwicEntry("right",
						"... the freedom of speech or of the press or the",
						"of the people peaceably to assemble and to petition the ..."),
				new KwicEntry(
						"right",
						"... being necessary to the security of a free State the",
						"of the people to keep and bear Arms shall not ..."),
				new KwicEntry("right", "The",
						"of the people to be secure in their persons houses ..."),
				new KwicEntry(
						"right",
						"In all criminal prosecutions the accused shall enjoy the",
						"to a speedy and public trial by an impartial jury ..."),
				new KwicEntry(
						"right",
						"... where the value in controversy shall exceed twenty dollars the",
						"of trial by jury shall be preserved and no fact ..."),
				new KwicEntry("rights",
						"The enumeration in the Constitution of certain",
						"shall not be construed to deny or disparage others retained ..."),
				new KwicEntry(
						"rules",
						"... any Court of the United States than according to the",
						"of the common law"),
				new KwicEntry(
						"searched",
						"... Oath or affirmation and particularly describing the place to be",
						"and the persons or things to be seized"),
				new KwicEntry(
						"searches",
						"... secure in their persons houses papers and effects against unreasonable",
						"and seizures shall not be violated and no Warrants shall ..."),
				new KwicEntry("secure", "The right of the people to be",
						"in their persons houses papers and effects against unreasonable searches ..."),
				new KwicEntry("security",
						"A well regulated Militia being necessary to the",
						"of a free State the right of the people to ..."),
				new KwicEntry("seized",
						"... to be searched and the persons or things to be",
						""),
				new KwicEntry(
						"seizures",
						"... their persons houses papers and effects against unreasonable searches and",
						"shall not be violated and no Warrants shall issue but ..."),
				new KwicEntry("service",
						"... or naval forces or in the Militia when in actual",
						"in time of War or public danger nor shall any ..."),
				new KwicEntry("shall", "Congress",
						"make no law respecting an establishment of religion or prohibiting ..."),
				new KwicEntry("shall",
						"... the right of the people to keep and bear Arms",
						"not be infringed"),
				new KwicEntry("shall", "No Soldier",
						"in time of peace be quartered in any house without ..."),
				new KwicEntry(
						"shall",
						"... persons houses papers and effects against unreasonable searches and seizures",
						"not be violated and no Warrants shall issue but upon ..."),
				new KwicEntry(
						"shall",
						"... searches and seizures shall not be violated and no Warrants",
						"issue but upon probable cause supported by Oath or affirmation ..."),
				new KwicEntry("shall", "No person",
						"be held to answer for a capital or otherwise infamous ..."),
				new KwicEntry(
						"shall",
						"... actual service in time of War or public danger nor",
						"any person be subject for the same offence to be ..."),
				new KwicEntry("shall",
						"... be twice put in jeopardy of life or limb nor",
						"be compelled in any criminal case to be a witness ..."),
				new KwicEntry(
						"shall",
						"... life liberty or property without due process of law nor",
						"private property be taken for public use without just compensation"),
				new KwicEntry("shall",
						"In all criminal prosecutions the accused",
						"enjoy the right to a speedy and public trial by ..."),
				new KwicEntry(
						"shall",
						"... impartial jury of the State and district wherein the crime",
						"have been committed which district shall have been previously ascertained ..."),
				new KwicEntry(
						"shall",
						"... district wherein the crime shall have been committed which district",
						"have been previously ascertained by law and to be informed ..."),
				new KwicEntry(
						"shall",
						"In Suits at common law where the value in controversy",
						"exceed twenty dollars the right of trial by jury shall ..."),
				new KwicEntry(
						"shall",
						"... shall exceed twenty dollars the right of trial by jury",
						"be preserved and no fact tried by a jury shall ..."),
				new KwicEntry("shall",
						"... shall be preserved and no fact tried by a jury",
						"be otherwise re-examined in any Court of the United States ..."),
				new KwicEntry("shall", "Excessive bail",
						"not be required nor excessive fines imposed nor cruel and ..."),
				new KwicEntry(
						"shall",
						"The enumeration in the Constitution of certain rights",
						"not be construed to deny or disparage others retained by ..."),
				new KwicEntry("Soldier", "No",
						"shall in time of peace be quartered in any house ..."),
				new KwicEntry(
						"speech",
						"... prohibiting the free exercise thereof or abridging the freedom of",
						"or of the press or the right of the people ..."),
				new KwicEntry(
						"speedy",
						"... criminal prosecutions the accused shall enjoy the right to a",
						"and public trial by an impartial jury of the State ..."),
				new KwicEntry(
						"State",
						"... regulated Militia being necessary to the security of a free",
						"the right of the people to keep and bear Arms ..."),
				new KwicEntry(
						"State",
						"... speedy and public trial by an impartial jury of the",
						"and district wherein the crime shall have been committed which ..."),
				new KwicEntry(
						"States",
						"... shall be otherwise re-examined in any Court of the United",
						"than according to the rules of the common law"),
				new KwicEntry("States",
						"The powers not delegated to the United",
						"by the Constitution nor prohibited by it to the States ..."),
				new KwicEntry(
						"States",
						"... States by the Constitution nor prohibited by it to the",
						"are reserved to the States respectively or to the people"),
				new KwicEntry(
						"States",
						"... prohibited by it to the States are reserved to the",
						"respectively or to the people"),
				new KwicEntry("subject",
						"... of War or public danger nor shall any person be",
						"for the same offence to be twice put in jeopardy ..."),
				new KwicEntry("Suits", "In",
						"at common law where the value in controversy shall exceed ..."),
				new KwicEntry(
						"supported",
						"... violated and no Warrants shall issue but upon probable cause",
						"by Oath or affirmation and particularly describing the place to ..."),
				new KwicEntry(
						"taken",
						"... without due process of law nor shall private property be",
						"for public use without just compensation"),
				new KwicEntry(
						"thereof",
						"... respecting an establishment of religion or prohibiting the free exercise",
						"or abridging the freedom of speech or of the press ..."),
				new KwicEntry(
						"things",
						"... describing the place to be searched and the persons or",
						"to be seized"),
				new KwicEntry("time", "No Soldier shall in",
						"of peace be quartered in any house without the consent ..."),
				new KwicEntry(
						"time",
						"... any house without the consent of the Owner nor in",
						"of war but in a manner to be prescribed by ..."),
				new KwicEntry(
						"time",
						"... forces or in the Militia when in actual service in",
						"of War or public danger nor shall any person be ..."),
				new KwicEntry(
						"trial",
						"... accused shall enjoy the right to a speedy and public",
						"by an impartial jury of the State and district wherein ..."),
				new KwicEntry(
						"trial",
						"... value in controversy shall exceed twenty dollars the right of",
						"by jury shall be preserved and no fact tried by ..."),
				new KwicEntry("tried",
						"... of trial by jury shall be preserved and no fact",
						"by a jury shall be otherwise re-examined in any Court ..."),
				new KwicEntry("twice",
						"... any person be subject for the same offence to be",
						"put in jeopardy of life or limb nor shall be ..."),
				new KwicEntry(
						"United",
						"... jury shall be otherwise re-examined in any Court of the",
						"States than according to the rules of the common law"),
				new KwicEntry("United", "The powers not delegated to the",
						"States by the Constitution nor prohibited by it to the ..."),
				new KwicEntry(
						"unless",
						"... held to answer for a capital or otherwise infamous crime",
						"on a presentment or indictment of a Grand Jury except ..."),
				new KwicEntry(
						"unreasonable",
						"... be secure in their persons houses papers and effects against",
						"searches and seizures shall not be violated and no Warrants ..."),
				new KwicEntry(
						"unusual",
						"... not be required nor excessive fines imposed nor cruel and",
						"punishments inflicted"),
				new KwicEntry(
						"use",
						"... of law nor shall private property be taken for public",
						"without just compensation"),
				new KwicEntry("value", "In Suits at common law where the",
						"in controversy shall exceed twenty dollars the right of trial ..."),
				new KwicEntry(
						"violated",
						"... and effects against unreasonable searches and seizures shall not be",
						"and no Warrants shall issue but upon probable cause supported ..."),
				new KwicEntry("war",
						"... without the consent of the Owner nor in time of",
						"but in a manner to be prescribed by law"),
				new KwicEntry("War",
						"... in the Militia when in actual service in time of",
						"or public danger nor shall any person be subject for ..."),
				new KwicEntry(
						"Warrants",
						"... unreasonable searches and seizures shall not be violated and no",
						"shall issue but upon probable cause supported by Oath or ..."),
				new KwicEntry("witness",
						"... shall be compelled in any criminal case to be a",
						"against himself nor be deprived of life liberty or property ..."),
				new KwicEntry(
						"witnesses",
						"... and cause of the accusation to be confronted with the",
						"against him to have compulsory process for obtaining witnesses in ..."),
				new KwicEntry(
						"witnesses",
						"... the witnesses against him to have compulsory process for obtaining",
						"in his favor and to have the Assistance of Counsel ..."), };

		final String inputFile = TEST_RESOURCE_BASE_PATH + "long_lines.txt";
		final String stopWordFile = TEST_RESOURCE_BASE_PATH + "long_lines_stopwords.txt";
		final KwicGenerator kwicGenerator = new KwicGenerator(inputFile,
				stopWordFile);
		final KwicIndex kwicIndex = kwicGenerator.generateKwicIndex();
		final List<KwicEntry> index = new ArrayList<KwicEntry>(kwicIndex.getIndex());
		Collections.sort(index);
		Assert.assertArrayEquals(expectedResults,
				index.toArray(new KwicEntry[] {}));
	}

	@Test
	public void getInputScanner_fromFile_successfully()
			throws FileNotFoundException {
		final String inputFile = TEST_RESOURCE_BASE_PATH + "inputfile.txt";
		final String stopWordFile = "";
		final KwicGenerator kwicGenerator = new KwicGenerator(inputFile,
				stopWordFile);
		final Scanner scanner = kwicGenerator.getInputScanner(inputFile);

		assertNotNull(scanner);
	}

	@Test
	public void getInputScanner_fromStdin_successfully()
			throws FileNotFoundException {
		final String inputFile = Constants.STDIN;
		final KwicGenerator kwicGenerator = new KwicGenerator(inputFile, "");
		final Scanner scanner = kwicGenerator.getInputScanner(inputFile);

		assertNotNull(scanner);
	}

	@Test(expected = FileNotFoundException.class)
	public void getInputScanner_badPath_throwsFileNotFoundException()
			throws FileNotFoundException {
		new KwicGenerator("", "");
	}

	@Test
	public void getStopWordList_successfully() throws FileNotFoundException {
		final String inputFile = Constants.STDIN;
		final String stopWordFile = TEST_RESOURCE_BASE_PATH + "stopwords.txt";
		final KwicGenerator kwicGenerator = new KwicGenerator(inputFile,
				stopWordFile);
		final List<String> stopWords = kwicGenerator
				.getStopWordList(stopWordFile);

		Assert.assertArrayEquals(
				new String[] { "these", "are", "stop", "words" },
				stopWords.toArray(new String[] {}));
	}

}
