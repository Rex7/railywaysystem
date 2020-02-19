<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:template match="transaction">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="simpleA4"
					page-height="29.7cm" page-width="21.0cm" margin="2cm"
					border-right-style="dashed">
					<fo:region-body />

				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="simpleA4">
				<fo:flow flow-name="xsl-region-body">
				<xsl:param name="myaddress"/>
					
					<fo:block font-size="14pt" margin-left="5cm">
						IRTC(Tatkal Ticket)
						<xsl:param name="name" />
						
						<xsl:value-of select="$name"></xsl:value-of>

					</fo:block>
					<fo:block margin-top="100pt" font-family="Helvetica"
						color="red" font-size="12pt" font-weight="bold">

						Name:
						<xsl:value-of select="customerName" />
					</fo:block>
					<fo:block margin-top="5pt">
					<xsl:param name="myaddress"/>
						Address:
						<xsl:value-of select="$myaddress" ></xsl:value-of>
					</fo:block>
					<fo:block>
					</fo:block>
					<fo:block>
						<fo:table margin-top="79pt" table-layout="fixed"
							border-width="1mm" border-style="solid">
							<fo:table-column column-width="2in" />
							<fo:table-column column-width="2in" />
							<fo:table-column column-width="1in" />
							<fo:table-column column-width="1in" />
							<fo:table-column column-width="1in" />
							<fo:table-header text-align="center"
								background-color="silver">
								<fo:table-row>
									<fo:table-cell padding="1mm" border-width="1mm"
										border-style="solid">
										<fo:block font-weight="bold">Name of the Person</fo:block>
									</fo:table-cell>
									<fo:table-cell padding="1mm" border-width="1mm"
										border-style="solid">
										<fo:block font-weight="bold">address</fo:block>
									</fo:table-cell>
									<fo:table-cell padding="1mm" border-width="1mm"
										border-style="solid">
										<fo:block font-weight="bold">No Of Passenger</fo:block>
									</fo:table-cell>
									<fo:table-cell padding="1mm" border-width="1mm"
										border-style="solid">
										<fo:block font-weight="bold">Cost</fo:block>
									</fo:table-cell>
									<fo:table-cell padding="1mm" border-width="1mm"
										border-style="solid">
										<fo:block font-weight="bold">TrainNo</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-header>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell padding="1mm" border-width="1mm"
										border-style="solid">
										<fo:block>
											<xsl:value-of select="customerName" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding="1mm" border-width="1mm"
										border-style="solid">
										<fo:block>
											<xsl:value-of select="$myaddress" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding="1mm" border-width="1mm"
										border-style="solid">
										<fo:block>
											<xsl:value-of select="noOfPassenger" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding="1mm" border-width="1mm"
										border-style="solid">
										<fo:block>
											<xsl:value-of select="totalCost" />
										</fo:block>
									</fo:table-cell>
										<fo:table-cell padding="1mm" border-width="1mm"
										border-style="solid">
										<fo:block>
											<xsl:value-of select="trainNo" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

							</fo:table-body>
						</fo:table>
					</fo:block>
					<fo:block margin-top="60pt" text-align="center">

						<fo:inline font-weight='bold'>keep origin id while traveling
						</fo:inline>
					</fo:block>


				</fo:flow>

			</fo:page-sequence>
		</fo:root>

	</xsl:template>
</xsl:stylesheet>